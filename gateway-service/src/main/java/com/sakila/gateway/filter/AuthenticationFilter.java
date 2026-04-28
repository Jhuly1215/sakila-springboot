package com.sakila.gateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Value("${jwt.secret}")
    private String secret;

    private final List<String> openPrefixes = List.of(
            "/auth/login",
            "/auth/register",
            "/swagger-ui",
            "/webjars",
            "/v3/api-docs",
            "/auth/v3/api-docs",
            "/customer/v3/api-docs");

    public AuthenticationFilter() {
        super(Config.class);
    }

    public static class Config {
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getURI().getPath();

            if (isOpenEndpoint(path)) {
                return chain.filter(exchange);
            }

            String token = extractToken(request);

            if (token == null || token.isBlank()) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            try {
                SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

                ServerHttpRequest mutatedRequest = request.mutate()
                        .header("loggedInUser", claims.getSubject())
                        .build();

                return chain.filter(exchange.mutate().request(mutatedRequest).build());

            } catch (Exception e) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
        };
    }

    private boolean isOpenEndpoint(String path) {
        if (path == null || path.isBlank()) {
            return false;
        }

        return "/swagger-ui.html".equals(path)
                || path.startsWith("/swagger-ui/")
                || path.startsWith("/webjars/")
                || path.equals("/v3/api-docs")
                || path.startsWith("/v3/api-docs/")
                || path.contains("/v3/api-docs")
                || openPrefixes.stream().anyMatch(path::startsWith);
    }

    private String extractToken(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return request.getQueryParams().getFirst("token");
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }
}
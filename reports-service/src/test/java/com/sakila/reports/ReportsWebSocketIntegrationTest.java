package com.sakila.reports;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ReportsWebSocketIntegrationTest {

    @Test
    void contextLoads() {
        // Verifica que el contexto de Spring levanta correctamente
        // incluyendo la configuracion de WebSocket
    }
}


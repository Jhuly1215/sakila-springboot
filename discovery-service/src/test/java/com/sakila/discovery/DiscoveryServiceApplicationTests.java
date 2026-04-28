package com.sakila.discovery;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
        classes = com.sakila.discovery.DiscoveryServiceApplication.class,
        properties = "spring.cloud.compatibility-verifier.enabled=false"
)
@ActiveProfiles("test")
class DiscoveryServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}

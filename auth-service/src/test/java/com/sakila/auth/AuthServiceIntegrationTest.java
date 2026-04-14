package com.sakila.auth;

import com.sakila.auth.dto.AuthRequest;
import com.sakila.auth.entity.Staff;
import com.sakila.auth.repository.StaffRepository;
import com.sakila.auth.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AuthServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        staffRepository.deleteAll();
        Staff staff = new Staff();
        staff.setFirstName("Test");
        staff.setLastName("User");
        staff.setUsername("testuser");
        staff.setPassword(passwordEncoder.encode("password"));
        staff.setEmail("test@test.com");
        staff.setActive(true);
        staffRepository.save(staff);
    }

    @Test
    void testLoginSuccess() throws Exception {
        String json = "{\"username\":\"testuser\", \"password\":\"password\"}";
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testLoginFailure() throws Exception {
        String json = "{\"username\":\"testuser\", \"password\":\"wrongpassword\"}";
        mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isForbidden());
    }
}

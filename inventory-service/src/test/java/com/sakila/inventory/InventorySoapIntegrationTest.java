package com.sakila.inventory;

import com.sakila.inventory.entity.Film;
import com.sakila.inventory.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

import javax.xml.transform.Source;
import java.math.BigDecimal;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

@SpringBootTest
@ActiveProfiles("test")
public class InventorySoapIntegrationTest {

    @Autowired
    private FilmRepository filmRepository;

    private MockWebServiceClient mockClient;

    @Autowired
    private org.springframework.context.ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        mockClient = MockWebServiceClient.createClient(applicationContext);
        filmRepository.deleteAll();
        Film film = new Film();
        film.setTitle("Test Film");
        film.setDescription("Description");
        film.setReleaseYear(2024);
        film.setLanguageId((byte) 1);
        film.setRentalDuration((byte) 3);
        film.setRentalRate(new BigDecimal("4.99"));
        film.setReplacementCost(new BigDecimal("19.99"));
        filmRepository.save(film);
    }

    @Test
    void testGetFilmSoap() throws Exception {
        Short id = filmRepository.findAll().get(0).getId();
        Source requestPayload = new StringSource(
                "<getFilmRequest xmlns=\"http://sakila.com/soap\">" +
                        "<id>" + id + "</id>" +
                        "</getFilmRequest>");

        Source responsePayload = new StringSource(
                "<getFilmResponse xmlns=\"http://sakila.com/soap\">" +
                        "<film>" +
                        "<id>" + id + "</id>" +
                        "<title>Test Film</title>" +
                        "<description>Description</description>" +
                        "<releaseYear>2024</releaseYear>" +
                        "</film>" +
                        "</getFilmResponse>");

        mockClient.sendRequest(withPayload(requestPayload))
                .andExpect(payload(responsePayload));
    }
}

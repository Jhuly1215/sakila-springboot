package com.sakila.inventory.soap;

import com.sakila.inventory.entity.Film;
import com.sakila.inventory.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InventoryEndpoint {

    private static final String NAMESPACE_URI = "http://sakila.com/soap";

    @Autowired
    private FilmRepository filmRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getFilmRequest")
    @ResponsePayload
    public GetFilmResponse getFilm(@RequestPayload GetFilmRequest request) {
        Film filmEntity = filmRepository.findById((short) request.getId())
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + request.getId()));

        GetFilmResponse response = new GetFilmResponse();
        FilmSoap filmSoap = new FilmSoap();

        filmSoap.setId(filmEntity.getId().intValue());
        filmSoap.setTitle(filmEntity.getTitle());
        filmSoap.setDescription(filmEntity.getDescription());
        filmSoap.setReleaseYear(
                filmEntity.getReleaseYear() != null ? filmEntity.getReleaseYear().intValue() : 0);

        response.setFilm(filmSoap);
        return response;
    }
}
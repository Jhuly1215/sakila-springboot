package com.sakila.inventory.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "film" })
@XmlRootElement(name = "getFilmResponse", namespace = "http://sakila.com/soap")
public class GetFilmResponse {
    @XmlElement(namespace = "http://sakila.com/soap", required = true)
    protected FilmSoap film;
    public FilmSoap getFilm() { return film; }
    public void setFilm(FilmSoap value) { this.film = value; }
}

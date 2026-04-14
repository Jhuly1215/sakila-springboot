package com.sakila.inventory.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filmSoap", namespace = "http://sakila.com/soap", propOrder = {
    "id",
    "title",
    "description",
    "releaseYear"
})
public class FilmSoap {
    @XmlElement(namespace = "http://sakila.com/soap")
    protected int id;
    @XmlElement(namespace = "http://sakila.com/soap", required = true)
    protected String title;
    @XmlElement(namespace = "http://sakila.com/soap", required = true)
    protected String description;
    @XmlElement(namespace = "http://sakila.com/soap")
    protected int releaseYear;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int value) { this.id = value; }
    public String getTitle() { return title; }
    public void setTitle(String value) { this.title = value; }
    public String getDescription() { return description; }
    public void setDescription(String value) { this.description = value; }
    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int value) { this.releaseYear = value; }
}

package com.sakila.inventory.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id" })
@XmlRootElement(name = "getFilmRequest", namespace = "http://sakila.com/soap")
public class GetFilmRequest {
    @XmlElement(namespace = "http://sakila.com/soap")
    protected int id;
    public int getId() { return id; }
    public void setId(int value) { this.id = value; }
}

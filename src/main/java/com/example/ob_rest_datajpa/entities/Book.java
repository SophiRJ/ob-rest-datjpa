package com.example.ob_rest_datajpa.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
@Table(name="books")
public class Book {
    //atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tittle;
    private String autor;
    private Integer pages;
    private Double price;
    private LocalDate releaseDate;
    private Boolean online;

    //Constructores
    public Book() {
    }

    public Book(Long id, String tittle, String autor, Integer pages, Double price, LocalDate releaseDate, Boolean online) {
        this.id = id;
        this.tittle = tittle;
        this.autor = autor;
        this.pages = pages;
        this.price = price;
        this.releaseDate = releaseDate;
        this.online = online;
    }

    //getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }
}

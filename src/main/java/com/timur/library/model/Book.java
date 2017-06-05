package com.timur.library.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public class Book {

    private String name;
    private Genre genre;
    private Integer id;
    private Integer amount;
    private List<Author> authors=new ArrayList<>();

    public Book() {

    }

    public Book(String name, Genre genre, Integer id, Integer amount) {
        this.name = name;
        this.genre = genre;
        this.id = id;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        authors.add(author);
    }
}

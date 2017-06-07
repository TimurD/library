package com.timur.library.models;

/**
 * Created by timur on 21.05.2017.
 */
public class Author {
    private Integer id;
    private String name;

    public Author() {

    }

    public Author(Integer id, String name) {

        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        return name != null ? name.equals(author.name) : author.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

package com.timur.library.entities;

/**
 * Created by timur on 21.05.2017.
 */
public class Genre {

    private String name;
    private Integer id;


    public Genre(Integer id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Genre() {

    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (name != null ? !name.equals(genre.name) : genre.name != null) return false;
        return id != null ? id.equals(genre.id) : genre.id == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}

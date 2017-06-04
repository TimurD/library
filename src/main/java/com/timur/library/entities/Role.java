package com.timur.library.entities;

/**
 * Created by timur on 21.05.2017.
 */
public class Role {
    private String name;
    private Integer id;


    public Role() {

    }

    public Role(String name, Integer id) {

        this.name = name;
        this.id = id;
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


}

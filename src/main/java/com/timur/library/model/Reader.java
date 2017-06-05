package com.timur.library.model;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public class Reader {
    private String name;
    private String password;
    private String email;
    private Integer id;
    private List<Role> roles;
    private int debt;

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reader() {

    }

    public Reader(String name, String password, String email) {

        this.name = name;
        this.password = password;
        this.email = email;
    }


    public String getName() {

        return name;
    }





}

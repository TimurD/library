package com.timur.library.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
    private Integer debt;
    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
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

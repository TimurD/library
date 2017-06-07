package com.timur.library.models;

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
    private Boolean host;

    public Reader() {
    }

    public Reader(String name, String password, String email, Integer id, List<Role> roles, Integer debt, Boolean admin, Boolean host) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.id = id;
        this.roles = roles;
        this.debt = debt;
        this.admin = admin;
        this.host = host;
    }

    public String getName() {
        return name;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getDebt() {
        return debt;
    }

    public void setDebt(Integer debt) {
        this.debt = debt;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getHost() {
        return host;
    }

    public void setHost(Boolean host) {
        this.host = host;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        return email != null ? email.equals(reader.email) : reader.email == null;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }
}

package com.timur.library.dao.factory;

/**
 * Created by timur on 21.05.2017.
 */
public enum DAOTypes {
    MySQL("MySQL"),PostgreSQL("PostgreSQL");

    private String name;

    DAOTypes(String name){
        this.name=name;
    }
}

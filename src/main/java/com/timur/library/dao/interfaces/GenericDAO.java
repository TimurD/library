package com.timur.library.dao.interfaces;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface GenericDAO<T> {
    T find(int id);
    List<T> findAll();
    void create(T t);
    void update(T t);
    void delete(T t);


}

package com.timur.library.dao.interfaces;

import com.timur.library.model.Reader;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface ReaderDAO{
    List<Reader>findAll();
    Reader findById(Integer id);
    List<Reader> findByName(String name);
    List<Reader> findByEmail(String email);
    Reader login(String email,String password);
    void create(Reader reader);
    List<Reader> findUsersForHost();

}

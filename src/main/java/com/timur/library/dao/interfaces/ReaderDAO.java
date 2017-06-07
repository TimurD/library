package com.timur.library.dao.interfaces;

import com.timur.library.models.Reader;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface ReaderDAO{
    /**
     *
     * @return all readers
     */
    List<Reader>findAll();

    /**
     *
     * @param id
     * @return reader by id
     */
    Reader findById(Integer id);

    /**
     *
     * @param name
     * @return reader which name start with param
     */
    List<Reader> findByName(String name);

    /**
     *
     * @param email
     * @return readers which email start with param
     */
    List<Reader> findByEmail(String email);

    /**
     *
     * @param email
     * @param password
     * @return reader by email and password if reader exist
     */
    Reader login(String email,String password);

    /**
     * create new reader
     * @param reader
     */
    void create(Reader reader);

    /**
     * find all users and their role
     * @return all users and their role
     */
    List<Reader> findUsersForHost();

}

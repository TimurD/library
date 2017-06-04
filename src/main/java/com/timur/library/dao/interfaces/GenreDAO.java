package com.timur.library.dao.interfaces;


import com.timur.library.entities.Genre;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface GenreDAO  {
    Genre findById(int id);
    List<Genre> findAll();
    Integer create(String genreName);
}
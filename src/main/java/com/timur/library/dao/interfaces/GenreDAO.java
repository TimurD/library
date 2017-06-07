package com.timur.library.dao.interfaces;


import com.timur.library.models.Genre;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface GenreDAO  {
    /**
     *
     * @param id
     * @return genre where id eq param
     */
    Genre findById(int id);

    /**
     *
     * @return all genres
     */
    List<Genre> findAll();

    /**
     *
     * @param genreName name for new genre
     * @return is genre created
     */
    Integer create(String genreName);
}

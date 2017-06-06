package com.timur.library.dao.interfaces;

import com.timur.library.models.Author;
import com.timur.library.models.Book;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface AuthorDAO{
    /**
     *
     * @param name
     * @return authors which name start with parameter
     */
    List<Author> findByName(String name);

    /**
     *
     * @param id
     * @return author by id
     */
    Author findById(int id);

    /**
     *
     * @return all authors
     */
    List<Author> findAll();

    /**
     *
     * @param authorName
     * @return is author created
     */
    Integer create(String authorName);

    /**
     *
     * @param book
     * @return all authors for book
     */
    List<Author> findBookAuthors(Book book);

}

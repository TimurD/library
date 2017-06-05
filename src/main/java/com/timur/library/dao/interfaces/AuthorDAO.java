package com.timur.library.dao.interfaces;

import com.timur.library.model.Author;
import com.timur.library.model.Book;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface AuthorDAO{
    List<Author> findByName(String name);
    Author findById(int id);
    List<Author> findAll();
    Integer create(String authorName);
    List<Author> findBookAuthors(Book book);

}

package com.timur.library.dao.interfaces;

import com.timur.library.entities.Book;
import com.timur.library.entities.Genre;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface BookDAO {

    List<Book> findByGenre(Integer genreId);
    Book findById(Integer id);
    List<Book> findByName(String name);
    List<Book> findAll();
    Integer create(String bookName,Integer genreId,Integer amount);
    List<Book> findAllBooksOfAuthor(Integer authorId);
    void addBookAmount(Integer id, Integer amount);
    List<Book> findAllBooksOfAuthor(String  authorName);
    void delete(Integer bookId);

}

package com.timur.library.dao.interfaces;

import com.timur.library.models.Book;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface BookDAO {

    /**
     *
     * @param genreId
     * @return book by genre
     */
    List<Book> findByGenre(Integer genreId);

    /**
     *
     * @param bookId
     * @return book by id
     */
    Book findById(Integer bookId);

    /**
     *
     * @param name
     * @return books which names start with parameter
     */
    List<Book> findByName(String name);

    /**
     *
     * @return all books
     */
    List<Book> findAll();

    /**
     *
     * @param bookName
     * @param genreId
     * @param amount
     * @return is book created
     */
    Integer create(String bookName,Integer genreId,Integer amount);

    /**
     *
     * @param authorId
     * @return all books of author
     */
    List<Book> findAllBooksOfAuthor(Integer authorId);

    /**
     *
     * @param bookId
     * @param amount new book amount
     */
    void setBookAmount(Integer bookId, Integer amount);

    /**
     *
     * @param authorName
     * @return all books where author name start with param
     */
    List<Book> findAllBooksOfAuthor(String  authorName);

    /**
     * delete book
     * @param bookId
     */
    void delete(Integer bookId);

}

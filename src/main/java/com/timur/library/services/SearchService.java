package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.models.*;

import java.util.List;

/**
 * Created by timur on 28.05.2017.
 */
public class SearchService {
    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory(DAOTypes.MySQL);
    ;
    private static volatile SearchService searchService;


    private SearchService() {

    }

    public static SearchService getInstance() {
        SearchService localInstance = searchService;
        if (localInstance == null) {
            synchronized (SearchService.class) {
                localInstance = searchService;
                if (localInstance == null) {
                    searchService = localInstance = new SearchService();
                }
            }
        }
        return localInstance;
    }

    /**
     * @return all books
     */
    public List<Book> findAllBooks() {
        return mySQLDAO.getBookDAO().findAll();
    }

    /**
     *
     * @param name
     * @return books which names start with parameter
     */
    public List<Book> findBooksByName(String name) {
        return mySQLDAO.getBookDAO().findByName(name);
    }

    /**
     *
     * @param genreId
     * @return book by genre
     */
    public List<Book> findBooksByGenre(Integer genreId) {
        return mySQLDAO.getBookDAO().findByGenre(genreId);
    }

    /**
     *
     * @param authorName
     * @return all books where author name start with param
     */
    public List<Book> findAllBookOfAuthor(String authorName) {
        return mySQLDAO.getBookDAO().findAllBooksOfAuthor(authorName);
    }

    /**
     *
     * @return all genres
     */
    public List<Genre> findAllGenres() {
        return mySQLDAO.getGenreDAO().findAll();
    }

    /**
     *
     * @return all authors
     */
    public List<Author> findAllAuthors() {
        return mySQLDAO.getAuthorDAO().findAll();
    }

    /**
     *
     * @param authorId
     * @return all books of author
     */
    public List<Book> allBooksOfAuthor(Integer authorId) {
        return mySQLDAO.getBookDAO().findAllBooksOfAuthor(authorId);
    }

    /**
     *
     * @param name
     * @return authors which name start with parameter
     */
    public List<Author> findAuthorsByName(String name) {
        return mySQLDAO.getAuthorDAO().findByName(name);
    }

    /**
     *
     * @param bookId
     * @return book by id
     */
    public Book findBooksById(Integer bookId) {
        return mySQLDAO.getBookDAO().findById(bookId);
    }
}



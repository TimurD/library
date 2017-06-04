package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.dao.factory.MySQLDAO;
import com.timur.library.entities.*;

import java.util.List;

/**
 * Created by timur on 28.05.2017.
 */
public class SearchService {
    private DAOFactory mySQLDAO= DAOFactory.getDAOFactory(DAOTypes.MySQL);;
    private static volatile SearchService searchService;


    private SearchService(){

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

    public List<Book>findAllBooks(){
        return mySQLDAO.getBookMySQLDAO().findAll();
    }


    public List<Book>findBooksByName(String name){
        return mySQLDAO.getBookMySQLDAO().findByName(name);
    }

    public List<Book>findBooksByGenre(Integer genreId){
        return mySQLDAO.getBookMySQLDAO().findByGenre(genreId);
    }


    public List<Book>findAllBookOfAuthor(String name){
        return mySQLDAO.getBookMySQLDAO().findAllBooksOfAuthor(name);
    }

    public List<Genre>findAllGenres(){
        return mySQLDAO.getGenreMySQLDAO().findAll();
    }

    public List<Author>findAllAuthors(){
        return mySQLDAO.getAuthorDAO().findAll();
    }



    public List<Book>allBooksOfAuthor(Integer id){
        return mySQLDAO.getBookMySQLDAO().findAllBooksOfAuthor(id);
    }

    public List<Author>findAuthorsByName(String name){
        return mySQLDAO.getAuthorDAO().findByName(name);
    }

    public Book findBooksById(Integer id) {
        return mySQLDAO.getBookMySQLDAO().findById(id);
    }
}



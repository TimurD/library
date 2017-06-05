package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.model.*;

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
        return mySQLDAO.getBookDAO().findAll();
    }


    public List<Book>findBooksByName(String name){
        return mySQLDAO.getBookDAO().findByName(name);
    }

    public List<Book>findBooksByGenre(Integer genreId){
        return mySQLDAO.getBookDAO().findByGenre(genreId);
    }


    public List<Book>findAllBookOfAuthor(String name){
        return mySQLDAO.getBookDAO().findAllBooksOfAuthor(name);
    }

    public List<Genre>findAllGenres(){
        return mySQLDAO.getGenreDAO().findAll();
    }

    public List<Author>findAllAuthors(){
        return mySQLDAO.getAuthorDAO().findAll();
    }



    public List<Book>allBooksOfAuthor(Integer id){
        return mySQLDAO.getBookDAO().findAllBooksOfAuthor(id);
    }

    public List<Author>findAuthorsByName(String name){
        return mySQLDAO.getAuthorDAO().findByName(name);
    }

    public Book findBooksById(Integer id) {
        return mySQLDAO.getBookDAO().findById(id);
    }
}



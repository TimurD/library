package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.dao.factory.MySQLDAO;
import com.timur.library.dao.mysqldao.BookMySQLDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 29.05.2017.
 */
public class AddingService {

    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory(DAOTypes.MySQL);
    ;
    private static volatile AddingService addingService;

    private AddingService() {

    }

    public static AddingService getInstance() {
        AddingService localInstance = addingService;
        if (localInstance == null) {
            synchronized (AddingService.class) {
                localInstance = addingService;
                if (localInstance == null) {
                    addingService = localInstance = new AddingService();
                }
            }
        }
        return localInstance;
    }


    public void addNewBook(String name, Integer amount, Integer genreId, List<String> authorIdsString) {
        List<Integer> authorIds = new ArrayList<>(authorIdsString.size());
        for (String authorId : authorIdsString) {
            authorIds.add(Integer.valueOf(authorId));
        }
        Integer bookId = mySQLDAO.getBookMySQLDAO().create(name, genreId, amount);
        mySQLDAO.getAuthorsBookMySQLDAO().create(authorIds, bookId);
    }

    public int addNewAuthor(String authorName) {
        return mySQLDAO.getAuthorDAO().create(authorName);
    }

    public int addNewGenre(String genreName) {
        return mySQLDAO.getGenreMySQLDAO().create(genreName);
    }
}

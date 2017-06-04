package com.timur.library.dao.factory;

import com.timur.library.dao.interfaces.*;
import com.timur.library.dao.mysqldao.*;

/**
 * Created by timur on 19.05.2017.
 */
public abstract class DAOFactory {

    public static DAOFactory getDAOFactory(DAOTypes daoTypes)  {

        switch (daoTypes) {
            case MySQL:
                return new MySQLDAO();
            case PostgreSQL: return null;
            default:
                return null;
        }
    }

    public abstract AuthorDAO getAuthorDAO();
    public abstract BookDAO getBookDAO();
    public abstract GenreDAO getGenreDAO();
    public abstract ReaderBookDAO getReaderBookDAO();
    public abstract ReaderDAO getReaderDAO();
    public abstract RoleDAO getRoleDAO();
    public abstract AuthorsBookDAO getAuthorsBookDAO();

}

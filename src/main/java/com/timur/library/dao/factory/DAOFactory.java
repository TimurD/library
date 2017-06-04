package com.timur.library.dao.factory;

import com.timur.library.dao.interfaces.AuthorDAO;
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

    public abstract AuthorMySQLDAO getAuthorDAO();
    public abstract BookMySQLDAO getBookMySQLDAO();
    public abstract GenreMySQLDAO getGenreMySQLDAO();
    public abstract ReaderBookMySQLDAO getReaderBookMySQLDAO();
    public abstract ReaderMySQLDAO getReaderMySQLDAO();
    public abstract RoleMySQLDAO getRoleMySQLDAO();
    public abstract AuthorsBookMySQLDAO getAuthorsBookMySQLDAO();

}

package com.timur.library.dao.factory;

import com.timur.library.dao.mysqldao.*;

/**
 * Created by timur on 21.05.2017.
 */
public class MySQLDAO extends DAOFactory {

   private static volatile MySQLDAO mySQLDAO;

   public static MySQLDAO getInstance() {
      MySQLDAO localInstance = mySQLDAO;
      if (localInstance == null) {
         synchronized (MySQLDAO.class) {
            localInstance = mySQLDAO;
            if (localInstance == null) {
               mySQLDAO = localInstance = new MySQLDAO();
            }
         }
      }
      return localInstance;
   }

   public AuthorMySQLDAO getAuthorDAO(){return AuthorMySQLDAO.getInstance();}
   public BookMySQLDAO getBookMySQLDAO(){return BookMySQLDAO.getInstance();}
   public GenreMySQLDAO getGenreMySQLDAO(){return GenreMySQLDAO.getInstance();}
   public ReaderBookMySQLDAO getReaderBookMySQLDAO(){return ReaderBookMySQLDAO.getInstance();}
   public ReaderMySQLDAO getReaderMySQLDAO(){return ReaderMySQLDAO.getInstance();}
   public RoleMySQLDAO getRoleMySQLDAO(){return RoleMySQLDAO.getInstance();}
   public AuthorsBookMySQLDAO getAuthorsBookMySQLDAO(){return AuthorsBookMySQLDAO.getInstance();}
}

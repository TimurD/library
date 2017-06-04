package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.entities.Reader;
import com.timur.library.entities.ReaderBook;

import java.util.List;

/**
 * Created by timur on 28.05.2017.
 */
public class AdminService {
    private DAOFactory mySQLDAO= DAOFactory.getDAOFactory(DAOTypes.MySQL);;
    private static volatile AdminService adminService;


    private AdminService(){

    }

    public static AdminService getInstance() {
        AdminService localInstance = adminService;
        if (localInstance == null) {
            synchronized (AdminService.class) {
                localInstance = adminService;
                if (localInstance == null) {
                    adminService = localInstance = new AdminService();
                }
            }
        }
        return localInstance;
    }

    public List<Reader>getInfoAboutAllReaders(){
        return mySQLDAO.getReaderMySQLDAO().findAll();
    }

    public List<Reader>getReadersByName(String name){
        return mySQLDAO.getReaderMySQLDAO().findByName(name);
    }

    public List<Reader>getReadersByEmail(String email){
        return mySQLDAO.getReaderMySQLDAO().findByEmail(email);
    }

    public List<ReaderBook>getBooksOfReaderForAdmin(Integer readerId){return mySQLDAO.getReaderBookMySQLDAO().findReaderBooksForAdmins(readerId);}

    public List<ReaderBook>getBooksOfReaderForReader(Integer readerId){return mySQLDAO.getReaderBookMySQLDAO().findReaderBooksForReader(readerId);}


    public void readerReturnBook(Integer id) {
        mySQLDAO.getReaderBookMySQLDAO().readerReturnBook(id);
    }

    public Reader getReaderById(Integer readerId){
        return mySQLDAO.getReaderMySQLDAO().findById(readerId);
    }

    public List<ReaderBook>getReadersForBook(Integer bookId){
        return mySQLDAO.getReaderBookMySQLDAO().findReadersForBook(bookId);
    }

    public List<ReaderBook> getBookOrders(){
        return mySQLDAO.getReaderBookMySQLDAO().findBookOrders();
    }

    public void getBookToReader(Integer id, Integer days){
        mySQLDAO.getReaderBookMySQLDAO().getBookToReader(id,days);
    }

    public List<ReaderBook> getBooksFromReadingRoom() {
        return mySQLDAO.getReaderBookMySQLDAO().findBooksForReadingRoom();
    }

    public void deleteBook(Integer bookId){
        mySQLDAO.getAuthorsBookMySQLDAO().delete(bookId);
        mySQLDAO.getBookMySQLDAO().delete(bookId);
    }

    public Boolean isBookOrdered(Integer bookId){
        return mySQLDAO.getReaderBookMySQLDAO().isBookOrdered(bookId);
    }
}

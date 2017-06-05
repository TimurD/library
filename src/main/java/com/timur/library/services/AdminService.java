package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.model.Reader;
import com.timur.library.model.ReaderBook;

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
        return mySQLDAO.getReaderDAO().findAll();
    }

    public List<Reader>getReadersByName(String name){
        return mySQLDAO.getReaderDAO().findByName(name);
    }

    public List<Reader>getReadersByEmail(String email){
        return mySQLDAO.getReaderDAO().findByEmail(email);
    }

    public List<ReaderBook>getBooksOfReaderForAdmin(Integer readerId){return mySQLDAO.getReaderBookDAO().findReaderBooksForAdmins(readerId);}

    public void readerReturnBook(Integer id) {
        mySQLDAO.getReaderBookDAO().readerReturnBook(id);
    }

    public Reader getReaderById(Integer readerId){
        return mySQLDAO.getReaderDAO().findById(readerId);
    }

    public List<ReaderBook>getReadersForBook(Integer bookId){
        return mySQLDAO.getReaderBookDAO().findReadersForBook(bookId);
    }

    public List<ReaderBook> getBookOrders(){
        return mySQLDAO.getReaderBookDAO().findBookOrders();
    }

    public void getBookToReader(Integer id, Integer days){
        mySQLDAO.getReaderBookDAO().getBookToReader(id,days);
    }

    public List<ReaderBook> getBooksFromReadingRoom() {
        return mySQLDAO.getReaderBookDAO().findBooksForReadingRoom();
    }

    public void deleteBook(Integer bookId){
        mySQLDAO.getReaderBookDAO().delete(bookId);
        mySQLDAO.getAuthorsBookDAO().delete(bookId);
        mySQLDAO.getBookDAO().delete(bookId);
    }

    public Boolean isBookOrdered(Integer bookId){
        return mySQLDAO.getReaderBookDAO().isBookOrdered(bookId);
    }

    public void setBookAmount(Integer bookId, Integer amount) {
        mySQLDAO.getBookDAO().setBookAmount(bookId,amount);
    }
}

package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.models.Reader;
import com.timur.library.models.ReaderBook;

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

    /**
     * find all readers
     * @return all readers
     */
    public List<Reader>getInfoAboutAllReaders(){
        return mySQLDAO.getReaderDAO().findAll();
    }

    /**
     *
     * @param name
     * @return reader where name start with param
     */
    public List<Reader>getReadersByName(String name){
        return mySQLDAO.getReaderDAO().findByName(name);
    }

    /**
     *
     * @param email
     * @return reader where name start with param
     */
    public List<Reader>getReadersByEmail(String email){
        return mySQLDAO.getReaderDAO().findByEmail(email);
    }

    /**
     * @param readerId
     * @return all books which reader take
     */

    public List<ReaderBook>getBooksOfReaderForAdmin(Integer readerId){return mySQLDAO.getReaderBookDAO().findReaderBooksForAdmins(readerId);}

    /**
     * @param readerBookId
     */
    public void readerReturnBook(Integer readerBookId) {
        mySQLDAO.getReaderBookDAO().readerReturnBook(readerBookId);
    }

    /**
     *
     * @param readerId
     * @return reader by id
     */
    public Reader getReaderById(Integer readerId){
        return mySQLDAO.getReaderDAO().findById(readerId);
    }


    /**
     *
     * @param bookId
     * @return all readers who take book
     */
    public List<ReaderBook>getReadersForBook(Integer bookId){
        return mySQLDAO.getReaderBookDAO().findReadersForBook(bookId);
    }

    /**
     *
     * @return all orders on books
     */
    public List<ReaderBook> getBookOrders(){
        return mySQLDAO.getReaderBookDAO().findBookOrders();
    }


    /**
     *get book to reader with readerId on n days
     * @param readerId
     * @param days
     */
    public void getBookToReader(Integer readerId, Integer days){
        mySQLDAO.getReaderBookDAO().getBookToReader(readerId,days);
    }

    /**
     *
     * @return all books in reading room
     */
    public List<ReaderBook> getBooksFromReadingRoom() {
        return mySQLDAO.getReaderBookDAO().findBooksForReadingRoom();
    }

    /**
     * delete all orders on book, all book authors, and book
     * @param bookId
     */
    public void deleteBook(Integer bookId){
        mySQLDAO.getReaderBookDAO().deleteForBook(bookId);
        mySQLDAO.getAuthorsBookDAO().delete(bookId);
        mySQLDAO.getBookDAO().delete(bookId);
    }

    /**
     *
     * @param bookId
     * @return is book ordered
     */
    public Boolean isBookOrdered(Integer bookId){
        return mySQLDAO.getReaderBookDAO().isBookOrdered(bookId);
    }

    /**
     *
     * @param bookId
     * @param amount new book amount
     */
    public void setBookAmount(Integer bookId, Integer amount) {
        mySQLDAO.getBookDAO().setBookAmount(bookId,amount);
    }
}

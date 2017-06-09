package com.timur.library.dao.interfaces;

import com.timur.library.models.ReaderBook;

import java.util.List;

/**
 * Created by timur on 05.06.2017.
 */
public interface ReaderBookDAO {

    /**
     *
     * @param readerId reader who take book
     * @param bookId book which reader take
     * @param isAdmin is reader admin
     * @return is success
     */
    Boolean readerTakeBook(Integer readerId, Integer bookId, Boolean isAdmin);

    /**
     *get book to reader with readerId on n days
     * @param readerId
     * @param days
     */
    void getBookToReader(Integer readerId, Integer days);

    /**
     * @param readerBookId
     */
    void readerReturnBook(Integer readerBookId);

    /**
     *
     * @param bookId
     * @return is book ordered
     */
    Boolean isBookOrdered(Integer bookId);

    /**
     *
     * @param readerId
     * @return is reader has debt
     */
    Boolean isReaderHasDebt(Integer readerId);

    /**
     *
     * @param bookId
     * @return all readers who take book
     */
    List<ReaderBook> findReadersForBook(Integer bookId,Integer fromBook);

    /**
     * @param readerId
     * @return all books which reader take
     */
    List<ReaderBook> findReaderBooksForAdmins(Integer readerId);

    /**
     *
     * @param readerId
     * @return all books which reader take or order
     */
    List<ReaderBook> findReaderBooksForReader(Integer readerId);

    /**
     *
     * @return all books in reading room
     */
    List<ReaderBook> findBooksForReadingRoom();

    /**
     *
     * @return all orders on books
     */
    List<ReaderBook> findBookOrders();

    /**
     * delete all orders on book
     * @param bookId
     */
    void deleteForBook(Integer bookId);

    /**
     * delete all orders of reader
     * @param readerId
     */
    void deleteForReader(Integer readerId);

    /**
     *
     * @param bookId
     * @return page count
     */
    Integer getPageCount(Integer bookId);
}

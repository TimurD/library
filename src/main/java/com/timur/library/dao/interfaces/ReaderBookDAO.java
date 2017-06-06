package com.timur.library.dao.interfaces;

import com.timur.library.models.ReaderBook;

import java.util.List;

/**
 * Created by timur on 05.06.2017.
 */
public interface ReaderBookDAO {

    Boolean readerTakeBook(Integer readerId, Integer bookId, Boolean isAdmin);
    void getBookToReader(Integer id, Integer days);
    void readerReturnBook(Integer id);
    Boolean isBookOrdered(Integer bookId);
    List<ReaderBook> findReadersForBook(Integer bookId);
    List<ReaderBook> findReaderBooksForAdmins(Integer readerId);
    List<ReaderBook> findReaderBooksForReader(Integer readerId);
    List<ReaderBook> findBooksForReadingRoom();
    List<ReaderBook> findBookOrders();
    Boolean readerNeedBook(Integer readerId);
    void deleteForBook(Integer bookId);
    void deleteForReader(Integer readerId);
}

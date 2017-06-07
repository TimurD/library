package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.models.ReaderBook;

import java.util.List;

/**
 * Created by timur on 02.06.2017.
 */
public class IssuanceBookService {
    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory(DAOTypes.MySQL);
    ;
    private static volatile IssuanceBookService issuanceBookService;


    private IssuanceBookService() {

    }

    public static IssuanceBookService getInstance() {
        IssuanceBookService localInstance = issuanceBookService;
        if (localInstance == null) {
            synchronized (IssuanceBookService.class) {
                localInstance = issuanceBookService;
                if (localInstance == null) {
                    issuanceBookService = localInstance = new IssuanceBookService();
                }
            }
        }
        return localInstance;
    }

    /**
     *  if reader admin book write on reading room, if reader not admin book write on reader
     * @param readerId
     * @param bookId
     * @param isAdmin
     * @return is success
     */
    public Boolean orderBook(Integer readerId, Integer bookId, boolean isAdmin) {
        return mySQLDAO.getReaderBookDAO().readerTakeBook(readerId, bookId,isAdmin);
    }

    /**
     *
     * @param readerId
     * @return all books which reader take or order
     */
    public List<ReaderBook>getBooksOfReaderForReader(Integer readerId){return mySQLDAO.getReaderBookDAO().findReaderBooksForReader(readerId);}



}

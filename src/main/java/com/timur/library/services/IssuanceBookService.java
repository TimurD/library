package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.entities.Role;

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

    public Integer queryOnBook(Integer readerId, Integer bookId, boolean isAdmin) {
        return mySQLDAO.getReaderBookMySQLDAO().readerTakeBook(readerId, bookId,isAdmin);
    }

    public boolean isAdmin(List<Role> roles) {
        for(Role role:roles){
            if(role.getName().equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }

}

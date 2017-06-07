package com.timur.library.services;

import com.timur.library.dao.factory.DAOFactory;
import com.timur.library.dao.factory.DAOTypes;
import com.timur.library.models.Reader;

import java.util.List;

/**
 * Created by timur on 05.06.2017.
 */
public class HostService {

    private static volatile HostService hostService;

    private HostService() {

    }

    public static HostService getInstance() {
        HostService localInstance = hostService;
        if (localInstance == null) {
            synchronized (HostService.class) {
                localInstance = hostService;
                if (localInstance == null) {
                    hostService = localInstance = new HostService();
                }
            }
        }
        return localInstance;
    }

    private DAOFactory mySQLDAO = DAOFactory.getDAOFactory(DAOTypes.MySQL);

    /**
     *
     * @return all users and their roles
     */
    public List<Reader> getUsersForHost(){
        return mySQLDAO.getReaderDAO().findUsersForHost();
    }

    /**
     * delete all orders on books and make user admin
     * @param userId
     */
    public void makeAdmin(Integer userId){
        mySQLDAO.getReaderBookDAO().deleteForReader(userId);
        mySQLDAO.getReaderRoleDAO().makeAdmin(userId);
    }


    /**
     * delete admin role for user
     * @param userId
     */
    public void unmakeAdmin(Integer userId){
        mySQLDAO.getReaderRoleDAO().unmakeAdmin(userId);
    }

    /**
     *
     * @param userId
     * @return is reader has debt
     */
    public boolean isReaderHasDebt(Integer userId) {
        return mySQLDAO.getReaderBookDAO().isReaderHasDebt(userId);
    }
}

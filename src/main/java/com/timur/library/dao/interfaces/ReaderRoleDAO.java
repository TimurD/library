package com.timur.library.dao.interfaces;

/**
 * Created by timur on 06.06.2017.
 */
public interface ReaderRoleDAO {

    /**
     * give admin rights
     * @param userId
     */
    void makeAdmin(Integer userId);

    /**
     * picks administrator rights
     * @param userId
     */
    void unmakeAdmin(Integer userId);
}

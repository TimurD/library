package com.timur.library.dao.interfaces;

/**
 * Created by timur on 06.06.2017.
 */
public interface ReaderRoleDAO {

    void makeAdmin(Integer userId);
    void unmakeAdmin(Integer userId);
}

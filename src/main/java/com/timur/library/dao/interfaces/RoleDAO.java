package com.timur.library.dao.interfaces;

import com.timur.library.models.Reader;
import com.timur.library.models.Role;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface RoleDAO{

    /**
     *
     * @param reader
     * @return all roles of reader
     */
    List<Role> findRolesForReader(Reader reader);
}

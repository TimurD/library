package com.timur.library.dao.interfaces;

import com.timur.library.models.Reader;
import com.timur.library.models.Role;

import java.util.List;

/**
 * Created by timur on 21.05.2017.
 */
public interface RoleDAO{
    List<Role> findRolesForReader(Reader reader);
}

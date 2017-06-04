package com.timur.library.dao.interfaces;

import java.util.List;

/**
 * Created by timur on 02.06.2017.
 */
public interface AuthorsBookDAO {

    void create(List<Integer> authorsId, Integer bookId);
    void delete(Integer bookId);
}

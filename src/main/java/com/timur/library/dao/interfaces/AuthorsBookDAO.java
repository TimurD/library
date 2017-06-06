package com.timur.library.dao.interfaces;

import java.util.List;

/**
 * Created by timur on 02.06.2017.
 */
public interface AuthorsBookDAO {

    /**
     * add authors for book
     * @param authorsId
     * @param bookId
     */
    void create(List<Integer> authorsId, Integer bookId);

    /**
     * delete author for book
     * @param bookId
     */
    void delete(Integer bookId);
}

package com.timur.library.commands;

import com.timur.library.model.Book;
import com.timur.library.managers.Config;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by timur on 30.05.2017.
 */
public class CommandAuthorBooks implements ICommand {


    private SearchService searchService =  SearchService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter("id"));
        List<Book>books = searchService.allBooksOfAuthor(id);
        request.setAttribute("books",books);
        String page= Config.getInstance().getProperty(Config.MAIN);
        return page;
    }
}

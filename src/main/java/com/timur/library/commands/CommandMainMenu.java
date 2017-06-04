package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 02.06.2017.
 */
public class CommandMainMenu implements ICommand {


    private SearchService searchService =  SearchService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("books", searchService.findAllBooks());
        return Config.getInstance().getProperty(Config.MAIN);
    }
}

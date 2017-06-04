package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 30.05.2017.
 */
public class CommandAuthorsSearch implements ICommand {


    private SearchService searchService =  SearchService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("search");
        if(name!=null){
            request.getSession().setAttribute("authors", searchService.findAuthorsByName(name));
        }
        String page= Config.getInstance().getProperty(Config.ADD_AUTHOR);
        return page;
    }
}

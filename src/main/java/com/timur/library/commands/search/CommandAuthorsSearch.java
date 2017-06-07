package com.timur.library.commands.search;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 30.05.2017.
 */
public class CommandAuthorsSearch implements ICommand {

    private static final String SEARCH_TEXT = "search";
    private SearchService searchService =  SearchService.getInstance();


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter(SEARCH_TEXT);
        if(name!=null){
            request.setAttribute("searchText",name);
            request.getSession().setAttribute("authors", searchService.getAuthorsByName(name));
        }
        String page= Config.getInstance().getProperty(Config.ADD_AUTHOR);
        return page;
    }
}

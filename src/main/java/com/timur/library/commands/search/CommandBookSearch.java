package com.timur.library.commands.search;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;

import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 28.05.2017.
 */
public class CommandBookSearch implements ICommand {

    private static final String SEARCH_TEXT = "search";
    private static final String SEARCH_CRITERIA = "selected";
    private static final String SEARCH_BY_BOOK_NAME = "bookName";

    private SearchService searchService =  SearchService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
        String criteria = request.getParameter(SEARCH_CRITERIA);
        if(criteria!=null) {
            String text = request.getParameter(SEARCH_TEXT);
            request.setAttribute("searchText",text);
            if (criteria.equals(SEARCH_BY_BOOK_NAME))
                request.getSession().setAttribute("books", searchService.findBooksByName(text));
            else
                request.getSession().setAttribute("books", searchService.findAllBookOfAuthor(text));
        }else {
            int genreId= Integer.parseInt(request.getParameter("id"));
            request.getSession().setAttribute("books", searchService.findBooksByGenre(genreId));
        }

        return Config.getInstance().getProperty(Config.MAIN);
    }
}

package com.timur.library.commands.adding;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.services.AddingService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by timur on 29.05.2017.
 */
public class CommandAddBook implements ICommand {


    private static final String NAME = "name";
    private static final String AMOUNT = "amount";
    private static final String GENRE_ID = "selectedGenre";
    private static final String AUTHORS="selectedAuthors";
    private static final String IS_IT_QUERY_ON_CREATING_BOOK="newBook";
    private SearchService searchService =  SearchService.getInstance();
    private AddingService addingService = AddingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Config.getInstance().getProperty(Config.ADD_BOOK);
        request.setAttribute("authors", searchService.getAllAuthors());
        if(request.getParameter(IS_IT_QUERY_ON_CREATING_BOOK)==null) {
            return page;
        }
        String bookName=request.getParameter(NAME);
        Integer amount= Integer.valueOf(request.getParameter(AMOUNT));
        Integer genreId= Integer.valueOf(request.getParameter(GENRE_ID));
        List<String> authors=new ArrayList<>(Arrays.asList(request.getParameterValues(AUTHORS)));

        addingService.addNewBook(bookName,amount,genreId,authors);
        return page;
    }
}

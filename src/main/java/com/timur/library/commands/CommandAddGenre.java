package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.manager.Message;
import com.timur.library.services.AddingService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 31.05.2017.
 */
public class CommandAddGenre implements ICommand {
    private static final String NAME = "name";

    private SearchService searchService =  SearchService.getInstance();
    private AddingService addingService = AddingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String genreName = request.getParameter(NAME);
        String page=Config.getInstance().getProperty(Config.ADD_GENRE);
        if(genreName==null){
            return page;
        }
        if(addingService.addNewGenre(genreName)==0){
            request.setAttribute("resultForGenre", Message.getInstance().getProperty(Message.GENRE_ALREADY_EXIST_ERROR));
        }else {
            request.setAttribute("resultForGenre", Message.getInstance().getProperty(Message.GENRE_ADDED));

        }
        request.getServletContext().setAttribute("genres", searchService.findAllGenres());
        return page;
    }
}

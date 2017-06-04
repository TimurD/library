package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.manager.Message;
import com.timur.library.services.AddingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 30.05.2017.
 */
public class CommandAddAuthor implements ICommand {

    private static final String NAME = "name";
    private AddingService addingService = AddingService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=Config.getInstance().getProperty(Config.ADD_AUTHOR);
        String authorName = request.getParameter(NAME);
        if(authorName==null){
            return page;
        }
        if(addingService.addNewAuthor(authorName)==0){
            request.setAttribute("resultForAuthor",Message.getInstance().getProperty(Message.AUTHOR_ALREADY_EXIST_ERROR));
        }else {
            request.setAttribute("resultForAuthor",Message.getInstance().getProperty(Message.AUTHOR_ADDED));
        }
        return page;
    }
}

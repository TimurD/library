/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.commands.authorization;

import com.timur.library.commands.ICommand;
import com.timur.library.models.Reader;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.services.AuthorizationService;
import com.timur.library.services.HostService;
import com.timur.library.services.SearchService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by timur on 31.05.2017.
 */
public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private SearchService searchService =  SearchService.getInstance();
    private AuthorizationService authorizationService = AuthorizationService.getInstance();
    private HostService hostService=HostService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Reader reader;
        reader= authorizationService.login(login,password);
        if (reader!=null) {
            request.getSession().setAttribute("user",reader);
            if(reader.getHost()){
                request.setAttribute("users",hostService.getUsersForHost());
                return Config.getInstance().getProperty(Config.HOST);
            }
            request.getSession().setAttribute("books", searchService.getAllBooks());
            request.getServletContext().setAttribute("genres", searchService.getAllGenres());
            return Config.getInstance().getProperty(Config.MAIN);
        }
            String locale=(String) request.getSession().getAttribute(LOCALE);
            request.setAttribute("error", Message.getInstance(locale).getString(Message.LOGIN_ERROR));
            return Config.getInstance().getProperty(Config.LOGIN);

    }
}

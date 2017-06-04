/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.commands;

import com.timur.library.entities.Reader;
import com.timur.library.manager.Config;
import com.timur.library.manager.Message;
import com.timur.library.services.AuthorizationService;
import com.timur.library.services.IssuanceBookService;
import com.timur.library.services.SearchService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAXIM
 */
public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    private SearchService searchService =  SearchService.getInstance();
    private AuthorizationService authorizationService = AuthorizationService.getInstance();
    private IssuanceBookService issuanceBookService=IssuanceBookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        Reader reader;
        reader= authorizationService.login(login,password);
        if (reader!=null) {
            request.getSession().setAttribute("user",reader);
            request.getSession().setAttribute("isAdmin",issuanceBookService.isAdmin(reader.getRoles()));
            request.getSession().setAttribute("books", searchService.findAllBooks());
            request.getServletContext().setAttribute("genres", searchService.findAllGenres());
            page = Config.getInstance().getProperty(Config.MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.LOGIN);
        }

        return page;
    }
}

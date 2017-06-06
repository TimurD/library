package com.timur.library.commands.authorization;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.services.AuthorizationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 29.05.2017.
 */
public class CommandRegistration implements ICommand {

    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private AuthorizationService authorizationService=AuthorizationService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale=(String) request.getSession().getAttribute(LOCALE);
        String name=request.getParameter(NAME);
        String password=request.getParameter(PASSWORD);
        String email=request.getParameter(EMAIL);
        boolean error=false;
        String page = null;
        request.setAttribute("email",email);
        request.setAttribute("name",name);
        if(!authorizationService.checkEmail(email)){
            request.setAttribute("emailError", Message.getInstance(locale).getString(Message.EMAIL_ERROR));
            error=true;
        }
        if(!authorizationService.checkName(name)){
            request.setAttribute("nameError",Message.getInstance(locale).getString(Message.NAME_ERROR));
            error=true;
        }
        if(!authorizationService.checkPassword(password)){
            request.setAttribute("passwordError",Message.getInstance(locale).getString(Message.PASSWORD_ERROR));
            error=true;
        }
        if(error)
            page = Config.getInstance().getProperty(Config.REGISTRATION);
        else {
            page = Config.getInstance().getProperty(Config.LOGIN);
            authorizationService.signUp(name,password,email);
        }
            return page;
    }
}

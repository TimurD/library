package com.timur.library.commands;

import com.timur.library.managers.Config;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by timur on 05.06.2017.
 */
public class CommandSwitchLang implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("locale", request.getParameter("lang"));
        return Config.getInstance().getProperty(Config.MAIN);
    }
}

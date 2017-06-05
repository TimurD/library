package com.timur.library.commands;

import com.timur.library.managers.Config;
import com.timur.library.services.AdminService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 29.05.2017.
 */
public class CommandReadersSearch implements ICommand {

    private static final String SEARCH_TEXT = "search";
    private static final String SEARCH_CRITERIA = "selected";
    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String text = request.getParameter(SEARCH_TEXT);
        String criteria = request.getParameter(SEARCH_CRITERIA);
        if(criteria.equals("Reader name"))
            request.setAttribute("readers", adminService.getReadersByName(text));
        else
            request.setAttribute("readers", adminService.getReadersByEmail(text));
        page = Config.getInstance().getProperty(Config.READERS);

        return page;
    }
}

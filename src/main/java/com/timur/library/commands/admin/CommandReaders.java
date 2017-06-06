package com.timur.library.commands.admin;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 28.05.2017.
 */
public class CommandReaders implements ICommand {
    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        request.setAttribute("readers", adminService.getInfoAboutAllReaders());
        page = Config.getInstance().getProperty(Config.READERS);

        return page;
    }
}


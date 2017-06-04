package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 02.06.2017.
 */
public class CommandOrders implements ICommand{
    private AdminService adminService=AdminService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("readerBooks",adminService.getBookOrders());
        return Config.getInstance().getProperty(Config.ORDERS);
    }
}

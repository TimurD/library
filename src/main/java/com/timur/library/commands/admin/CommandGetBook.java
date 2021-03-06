package com.timur.library.commands.admin;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 03.06.2017.
 */
public class CommandGetBook implements ICommand {
    private AdminService adminService=AdminService.getInstance();
    private final static String BOOK_ID="id";
    private final static String DAYS="days";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id= Integer.valueOf(request.getParameter(BOOK_ID));
        Integer days=Integer.valueOf(request.getParameter(DAYS));
        adminService.getBookToReader(id,days);
        request.setAttribute("readerBooks",adminService.getBookOrders());
        return Config.getInstance().getProperty(Config.ORDERS);
    }
}

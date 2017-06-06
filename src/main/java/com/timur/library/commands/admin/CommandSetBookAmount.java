package com.timur.library.commands.admin;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.services.AdminService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 05.06.2017.
 */
public class CommandSetBookAmount implements ICommand {
    private AdminService adminService = AdminService.getInstance();
    private SearchService searchService = SearchService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId = Integer.valueOf(request.getParameter("bookId"));
        Integer amount=Integer.valueOf(request.getParameter("amount"));
        adminService.setBookAmount(bookId,amount);
        request.getSession().setAttribute("books", searchService.findAllBooks());
        return Config.getInstance().getProperty(Config.MAIN);
    }
}

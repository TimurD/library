package com.timur.library.commands;

import com.timur.library.manager.Config;
import com.timur.library.manager.Message;
import com.timur.library.services.AdminService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 04.06.2017.
 */
public class CommandDeleteBook implements ICommand {
    private AdminService adminService=AdminService.getInstance();
    private SearchService searchService=SearchService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer bookId=Integer.valueOf(request.getParameter("bookId"));
        if(adminService.isBookOrdered(bookId)){
            request.setAttribute("message", Message.getInstance().getProperty(Message.CANT_DELETE_BOOK));
        }else {
            adminService.deleteBook(bookId);
            request.getSession().setAttribute("books", searchService.findAllBooks());
            request.setAttribute("message", Message.getInstance().getProperty(Message.BOOK_DELETED));
        }
        return Config.getInstance().getProperty(Config.MAIN);
    }
}

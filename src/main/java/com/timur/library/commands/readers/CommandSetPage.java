package com.timur.library.commands.readers;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.models.Book;
import com.timur.library.services.AdminService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 09.06.2017.
 */
public class CommandSetPage implements ICommand {
    private AdminService adminService=AdminService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer page= Integer.valueOf(request.getParameter("page"));
        Integer fromBook= (page-1)*5;
        Book book= (Book) request.getSession().getAttribute("book");
        request.getSession().setAttribute("readerBooks",adminService.getReadersForBook(book.getId(),fromBook));
        return Config.getInstance().getProperty(Config.BOOK_LENDERS);
    }
}

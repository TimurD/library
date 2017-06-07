package com.timur.library.commands.admin;

import com.timur.library.commands.ICommand;
import com.timur.library.models.Book;
import com.timur.library.models.ReaderBook;
import com.timur.library.managers.Config;
import com.timur.library.services.AdminService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by timur on 29.05.2017.
 */
public class CommandBookLenders implements ICommand {


    private SearchService searchService =  SearchService.getInstance();
    private AdminService adminService=AdminService.getInstance();
    private final static String READER_ID="id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id= Integer.parseInt(request.getParameter(READER_ID));
        Book book= searchService.getBooksById(id);
        request.getSession().setAttribute("book",book);
        List<ReaderBook>readerBooks= adminService.getReadersForBook(id);
        request.setAttribute("readerBooks",readerBooks);
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("currentDate", timestamp);
        return Config.getInstance().getProperty(Config.BOOK_LENDERS);
    }
}

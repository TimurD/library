package com.timur.library.commands;

import com.timur.library.entities.Book;
import com.timur.library.entities.Reader;
import com.timur.library.entities.ReaderBook;
import com.timur.library.manager.Config;
import com.timur.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by timur on 31.05.2017.
 */
public class CommandReturnBook implements ICommand {

    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=null;
        Integer id= Integer.valueOf(request.getParameter("id"));
        adminService.readerReturnBook(id);
        String queryFrom=request.getParameter("page");
        List<ReaderBook> readerBooks;
        if(queryFrom.equals("readerInfo")) {
            Reader reader = (Reader) request.getSession().getAttribute("reader");
            readerBooks = adminService.getBooksOfReaderForAdmin(reader.getId());
            page= Config.getInstance().getProperty(Config.READER_INFO);
        }else if(queryFrom.equals("bookLenders")) {
            Book book=(Book)request.getSession().getAttribute("book");
            readerBooks = adminService.getReadersForBook(book.getId());
            page= Config.getInstance().getProperty(Config.BOOK_LENDERS);
        }else if(queryFrom.equals("readingRoom")) {
            readerBooks = adminService.getBooksFromReadingRoom();
            page= Config.getInstance().getProperty(Config.READING_ROOM);
        }else {
            readerBooks = adminService.getBookOrders();
            page= Config.getInstance().getProperty(Config.ORDERS);
        }
        request.setAttribute("readerBooks", readerBooks);
        return page;
    }
}

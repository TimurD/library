package com.timur.library.commands;

import com.timur.library.entities.Reader;
import com.timur.library.entities.ReaderBook;
import com.timur.library.manager.Config;
import com.timur.library.services.AdminService;
import com.timur.library.services.IssuanceBookService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by timur on 30.05.2017.
 */
public class CommandReaderInfo implements ICommand {

    private AdminService adminService = AdminService.getInstance();
    private IssuanceBookService issuanceBookService = IssuanceBookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader currentUser = (Reader) request.getSession().getAttribute("user");
        List<ReaderBook> readerBooks;
        if (issuanceBookService.isAdmin(currentUser.getRoles())) {
            int readerId = Integer.parseInt(request.getParameter("id"));
            request.getSession().setAttribute("reader", adminService.getReaderById(readerId));
            readerBooks = adminService.getBooksOfReaderForAdmin(readerId);
        } else {
            readerBooks = adminService.getBooksOfReaderForReader(currentUser.getId());
        }
        request.setAttribute("readerBooks", readerBooks);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("currentDate", timestamp);
        return Config.getInstance().getProperty(Config.READER_INFO);
    }
}

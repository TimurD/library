package com.timur.library.commands.admin;

import com.timur.library.commands.ICommand;
import com.timur.library.models.Reader;
import com.timur.library.models.ReaderBook;
import com.timur.library.managers.Config;
import com.timur.library.services.AdminService;
import com.timur.library.services.AuthorizationService;
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
    private AuthorizationService authorizationService=AuthorizationService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader currentUser = (Reader) request.getSession().getAttribute("user");
        List<ReaderBook> readerBooks;
        if (authorizationService.isAdmin(currentUser.getRoles())) {
            int readerId = Integer.parseInt(request.getParameter("id"));
            request.getSession().setAttribute("reader", adminService.getReaderById(readerId));
            readerBooks = adminService.getBooksOfReaderForAdmin(readerId);
        } else {
            readerBooks = issuanceBookService.getBooksOfReaderForReader(currentUser.getId());
        }
        request.setAttribute("readerBooks", readerBooks);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        request.getSession().setAttribute("currentDate", timestamp);
        return Config.getInstance().getProperty(Config.READER_INFO);
    }
}

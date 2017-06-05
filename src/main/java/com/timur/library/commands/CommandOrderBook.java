package com.timur.library.commands;

import com.timur.library.model.Reader;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.services.IssuanceBookService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 02.06.2017.
 */
public class CommandOrderBook implements ICommand {
    private IssuanceBookService issuanceBookService=IssuanceBookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader= (Reader) request.getSession().getAttribute("user");
        Integer bookId= Integer.valueOf(request.getParameter("id"));
        Integer readerId=reader.getId();
        Reader user= (Reader) request.getSession().getAttribute("user");
        Boolean isAdmin=(Boolean)request.getSession().getAttribute("isAdmin");
        if(!issuanceBookService.queryOnBook(readerId,bookId,isAdmin)){
            request.getSession().setAttribute("message", Message.getInstance().getProperty(Message.BOOK_IS_NOT_AVAILABLE));
        }else{
            request.getSession().setAttribute("message", Message.getInstance().getProperty(Message.BOOK_ORDERED));

        }
        response.sendRedirect(Config.getInstance().getProperty(Config.MAIN));
        return null;
    }
}

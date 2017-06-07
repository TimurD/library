package com.timur.library.commands.readers;

import com.timur.library.commands.ICommand;
import com.timur.library.models.Reader;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.services.IssuanceBookService;
import com.timur.library.services.SearchService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 02.06.2017.
 */
public class CommandOrderBook implements ICommand {
    private IssuanceBookService issuanceBookService=IssuanceBookService.getInstance();
    private SearchService searchService=SearchService.getInstance();

    private final static String BOOK_ID="id";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String locale=(String) request.getSession().getAttribute(LOCALE);
        Reader user= (Reader) request.getSession().getAttribute(CURRENT_USER);
        Integer bookId= Integer.valueOf(request.getParameter(BOOK_ID));
        Integer readerId=user.getId();
        Boolean isAdmin=user.getAdmin();
        if(!issuanceBookService.orderBook(readerId,bookId,isAdmin)){
            request.getSession().setAttribute("message", Message.getInstance(locale).getString(Message.BOOK_IS_NOT_AVAILABLE));
        }else{
            request.getSession().setAttribute("message", Message.getInstance(locale).getString(Message.BOOK_ORDERED));
            request.getSession().setAttribute("books", searchService.findAllBooks());
        }
        response.sendRedirect(Config.getInstance().getProperty(Config.MAIN));
        return null;
    }
}

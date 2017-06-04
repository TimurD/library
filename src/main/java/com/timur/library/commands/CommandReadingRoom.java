package com.timur.library.commands;

import com.timur.library.entities.ReaderBook;
import com.timur.library.manager.Config;
import com.timur.library.services.AdminService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by timur on 03.06.2017.
 */
public class CommandReadingRoom implements ICommand{

    private AdminService adminService=AdminService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ReaderBook> readerBooks = adminService.getBooksFromReadingRoom();
        request.setAttribute("readerBooks", readerBooks);
        return Config.getInstance().getProperty(Config.READING_ROOM);
    }
}

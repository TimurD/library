package com.timur.library.servlet;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Message;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 28.05.2017.
 */
public class Controller extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(Controller.class);


    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        String locale= (String) request.getSession().getAttribute("locale");
        try {
            ICommand command = controllerHelper.getCommand(request);
            page = command.execute(request, response);
        } catch (ServletException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("messageError", Message.getInstance(locale).getString(Message.SERVLET_EXECPTION));

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("messageError", Message.getInstance(locale).getString(Message.IO_EXCEPTION));

        }
        if(page!=null) {
            //test for repository
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
}

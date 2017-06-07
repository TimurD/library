/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.commands;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MAXIM
 */
public interface ICommand {
    String LOCALE="locale";

    /**
     * this method returns relative address of jsp file, which will be forwarded by servlet.
     * also additional services inside method may be called when needed.
     * @param request takes http request from servlet.
     * @param response takes http response from servlet.
     * @return address of .jsp file
     * @throws ServletException
     * @throws IOException
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}

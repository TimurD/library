/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.commands.readers;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by timur on 31.05.2017.
 */
public class CommandMissing implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession(false)==null||request.getSession().getAttribute("user")==null) {
            return Config.getInstance().getProperty(Config.LOGIN);
        }else return Config.getInstance().getProperty(Config.MAIN);
    }
}

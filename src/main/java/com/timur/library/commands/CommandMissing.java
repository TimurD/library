/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.timur.library.commands;

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
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}

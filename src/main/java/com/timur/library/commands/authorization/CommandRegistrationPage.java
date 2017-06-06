package com.timur.library.commands.authorization;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 04.06.2017.
 */
public class CommandRegistrationPage implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return Config.getInstance().getProperty(Config.REGISTRATION);
    }
}

package com.timur.library.commands;

import com.timur.library.managers.Config;
import com.timur.library.services.AuthorizationService;
import com.timur.library.services.HostService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by timur on 05.06.2017.
 */
public class CommandSetRole implements ICommand {
    private HostService hostService=HostService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId= Integer.valueOf(request.getParameter("userId"));
        Boolean admin= Boolean.valueOf(request.getParameter("admin"));
        if(admin){
            if(hostService.userNeedBook(userId)) {
                hostService.unmakeAdmin(userId);
            }else {
                request.setAttribute("message","reader need return books");//TODO
            }
        }else{
            hostService.makeAdmin(userId);
        }
        request.setAttribute("users",hostService.getUsersForHost());
        return Config.getInstance().getProperty(Config.HOST);
    }
}

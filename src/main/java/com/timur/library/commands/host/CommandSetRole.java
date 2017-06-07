package com.timur.library.commands.host;

import com.timur.library.commands.ICommand;
import com.timur.library.managers.Config;
import com.timur.library.managers.Message;
import com.timur.library.models.Reader;
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
    private AuthorizationService authorizationService=AuthorizationService.getInstance();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Reader reader=(Reader)request.getSession().getAttribute("user");

        if(!reader.getHost()){
            return Config.getInstance().getProperty(Config.MAIN);
        }
        Integer userId= Integer.valueOf(request.getParameter("userId"));
        Boolean admin= Boolean.valueOf(request.getParameter("admin"));
        String locale= (String) request.getSession().getAttribute(LOCALE);
        if(!admin){
            if(!hostService.isReaderHasDebt(userId)) {
                hostService.makeAdmin(userId);
            }else {
                request.setAttribute("message", Message.getInstance(locale).getString(Message.USER_NEED_RETURN_BOOKS));
            }
        }else{
            hostService.unmakeAdmin(userId);
        }
        request.setAttribute("users",hostService.getUsersForHost());
        return Config.getInstance().getProperty(Config.HOST);
    }
}

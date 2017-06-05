package com.timur.library.filters;

import com.timur.library.managers.Config;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by timur on 02.06.2017.
 */
public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean allowedRequest = false;
        String command=request.getParameter("command");
        if(command!=null&&(command.equals("login")||command.equals("registration")||command.equals("registrationPage"))) {
            allowedRequest = true;
        }
        if (!allowedRequest&&(request.getSession(false)==null||request.getSession().getAttribute("user")==null)) {
                response.sendRedirect(Config.getInstance().getProperty(Config.LOGIN));
        }else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
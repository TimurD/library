package com.timur.library.filters;

import com.timur.library.managers.Config;
import com.timur.library.models.Reader;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timur on 07.06.2017.
 */
public class AccessFilter implements Filter {

    private List<String> allowedCommands = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedCommands.add("searchBooks");
        allowedCommands.add("allOfAuthor");
        allowedCommands.add("searchAuthor");
        allowedCommands.add("logout");
        allowedCommands.add("mainMenu");
        allowedCommands.add("orderBook");
        allowedCommands.add("switchLang");
        allowedCommands.add("readerInfo");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if(request.getSession(false)==null||request.getSession().getAttribute("user")==null){
            filterChain.doFilter(request,response);
        }else {
            Reader user= (Reader) request.getSession().getAttribute("user");
            String command=request.getParameter("command");
            if(!user.getAdmin()&&!allowedCommands.contains(command)){
                response.sendRedirect(Config.getInstance().getProperty(Config.MAIN));
            }else {
                filterChain.doFilter(request,response);
            }
        }

    }

    @Override
    public void destroy() {

    }
}

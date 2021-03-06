package com.hundred.listener;

import com.hundred.vo.User;
import com.mysql.cj.api.Session;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class MyServletContextListener implements ServletContextListener{
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Map<User,HttpSession> userMap = new HashMap<User,HttpSession>();
        servletContextEvent.getServletContext().setAttribute("userMap",userMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

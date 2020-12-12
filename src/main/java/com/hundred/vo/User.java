package com.hundred.vo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;

public class User implements HttpSessionBindingListener{

    private Integer id;
    private String username;
    private String password;
    private String type;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        //每当存入一个对象在session里面的时候，自动存入一个对象到人员列表UserMap里面
        System.out.println("进来了。。。");
        HttpSession session = httpSessionBindingEvent.getSession();
        Map<User,HttpSession> map = (Map<User,HttpSession>)session.getServletContext()
                .getAttribute("userMap");
        map.put(this,session);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        System.out.println("退出了。。。");
        HttpSession session = httpSessionBindingEvent.getSession();
        Map<User,HttpSession> map = (Map<User,HttpSession>)session.getServletContext()
                .getAttribute("userMap");
        map.remove(this);
    }
}

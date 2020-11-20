package com.hundred.servlet;

import com.hundred.service.UserService;
import com.hundred.utils.BaseServlet;
import com.hundred.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UserServlet extends BaseServlet {

    public String login(HttpServletRequest req,HttpServletResponse res){
        //接收参数
        Map<String,String[]> userMap = req.getParameterMap();
        //封装参数
        User user = new User();
        try {
            BeanUtils.populate(user,userMap);
            //调用service层
            UserService userService = new UserService();
            User exsitUser = userService.login(user);
            //判断登录用户空值
            if(exsitUser == null){
                req.setAttribute("msg","用户名密码错误！");
                return "/index.jsp";
            }else{
                //重定向页面
                res.sendRedirect(req.getContextPath()+"/main.jsp");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

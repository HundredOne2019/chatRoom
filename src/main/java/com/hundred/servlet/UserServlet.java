package com.hundred.servlet;

import com.hundred.service.UserService;
import com.hundred.utils.BaseServlet;
import com.hundred.vo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            User existUser = userService.login(user);
            //判断登录用户空值
            if(existUser == null){
                req.setAttribute("msg","用户名密码错误！");
                return "/index.jsp";
            }else{
                //1.销毁上一个登录用户的session
                req.getSession().invalidate();
                //2.判断Map里是否有相同的用户,有则销毁
                Map<User,HttpSession> contextMap = (Map<User,HttpSession>)getServletContext().
                        getAttribute("userMap");
                if(contextMap.containsKey(existUser)){
                    HttpSession session = contextMap.get(existUser);
                    session.invalidate();
                }
                //3.把用户信息存在session里面,并使用session绑定监听器保存此对象到用户列表里面
                req.getSession().setAttribute("existUser",existUser);
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

package com.hundred.service;

import com.hundred.action.UserDao;
import com.hundred.action.impl.UserDaoImpl;
import com.hundred.vo.User;

public class UserService {
    public User login(User user){
        UserDao userDao = new UserDaoImpl();
        User existUser = userDao.login(user);
        return existUser;
    }
}

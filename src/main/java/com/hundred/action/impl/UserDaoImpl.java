package com.hundred.action.impl;

import com.hundred.action.UserDao;
import com.hundred.utils.JDBCUtils;
import com.hundred.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

public class UserDaoImpl implements UserDao{

    @Override
    public User login(User user) {
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ?";
        User exsitUser = new User();
        try {
            exsitUser = queryRunner.query(sql,new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            new RuntimeException("登录失败！");
        }
        return exsitUser;
    }
}

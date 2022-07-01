package com.blog.back.service.impl;

import com.blog.back.domain.User;
import com.blog.back.mapper.UserMapper;
import com.blog.back.service.UserService;
import com.blog.base.exception.BlogEnum;
import com.blog.base.exception.BlogException;
import com.blog.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    public User login(User user, String code, String rightCode) {
        //校验验证码
        if (!rightCode.equals(code)){
            throw new BlogException(BlogEnum.USER_LOGIN_CODE);
        }
        //把密码进行加密
        String password=user.getPassword();
        password= MD5Util.getMD5(password);
        user.setPassword(password);
        //检验用户名和密码
        List<User> users = userMapper.select(user);
        if (users.size()==0){
            throw new BlogException(BlogEnum.USER_LOGIN_ACCOUNT);
        }
        //因为满足用户条件的只有一个
        return users.get(0);
    }


    public void verifyooldPwd(String oldPwd, User user) {
        String password = user.getPassword();
        oldPwd=MD5Util.getMD5(oldPwd);
        if (!password.equals(oldPwd)){
            throw new BlogException(BlogEnum.USER_VERIFY_PASS);
        }
    }

    public void updateUser(User user) {
        //给用户输入新密码进行加密
        user.setPassword(MD5Util.getMD5(user.getPassword()));
        //此方法是跟新非空字段  count:影响记录数
        int count = userMapper.updateByPrimaryKeySelective(user);

        if (count==0){
            throw new BlogException(BlogEnum.USER_UPDATE);
        }
    }
}

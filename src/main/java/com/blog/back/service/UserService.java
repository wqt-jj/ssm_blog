package com.blog.back.service;

import com.blog.back.domain.User;
import com.blog.base.bean.ResultVo;

public interface UserService {
    User login(User user, String code, String rightCode);


    void verifyooldPwd(String oldPwd, User user);

    void updateUser(User user);
}

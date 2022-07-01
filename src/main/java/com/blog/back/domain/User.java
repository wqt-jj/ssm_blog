package com.blog.back.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data

@Table(name ="t_user")
@NameStyle(Style.normal)/*括号里面是枚举类型的意思    加入此注解防止isOpen没有按照is_open的标准来
加入此注解可以自己想用什么类型就用什么类型*/
public class User {
    @Id
    private String uid;
    private String username;
    private String nickname;
    private String password;
    private String role;
    private String createTime;
    private String lastLoginTime;
    private String state;
    private String loginIp;
    private String phone;
    private String loginCout;
    private String img;

}

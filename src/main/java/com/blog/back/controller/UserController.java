package com.blog.back.controller;

import com.blog.back.domain.User;
import com.blog.back.service.UserService;
import com.blog.base.bean.ResultVo;
import com.blog.base.exception.BlogException;
import com.blog.base.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/*
* 1.通过自定义异常来定义系统中出现异常的情况
* 2、使用枚举实现用户操作失败的定义情况
* 3、使用ResultVo给客户端返回具体操作的结果信息
* */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public ResultVo login(User user, String code, HttpSession session){
        ResultVo resultVo = new ResultVo();
        //从session获取正确的验证码
            String rightCode= (String) session.getAttribute("code");

            try {
                user =userService.login(user,code,rightCode);
                resultVo.setOk(true);
                //在后台首页显示登录用户的昵称
                session.setAttribute("user",user);
            }catch (BlogException e){
                resultVo.setMess(e.getMessage());
            }
            return resultVo;
    }

    //用户名登录成功后跳转到后台首页
    @RequestMapping("/workbench/index")
    public String index(){
        return "workbench/index";
    }

    //退出登录
    @RequestMapping("/loginOut")
    public String loginOut(HttpSession session){
        //清除session中的用户
        session.removeAttribute("user");
        //重定向到登录页面
        return "redirect:/login.jsp";
    }

    //异步校验原始密码
    @RequestMapping("/verifyooldPwd")
    @ResponseBody
    public ResultVo verifyooldPwd(String oldPwd,HttpSession session){
        ResultVo resultVo = new ResultVo();

        try {
           //获取当前登录用户
            User user= (User) session.getAttribute("user");
            userService.verifyooldPwd(oldPwd,user);
            resultVo.setOk(true);
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;

    }

    //异步修改用户信息
    @RequestMapping("/updateUser")
    @ResponseBody
    public ResultVo updateUser(User user){
        ResultVo resultVo = new ResultVo();

        try {

            userService.updateUser(user);
            resultVo.setOk(true);
            resultVo.setMess("修改用户信息成功");
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }
        return resultVo;

    }
}

package com.blog.base.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController/*Spring4之后新加入的注解，原来返回json需要@ResponseBody和@Controller配合。
即@RestController是@ResponseBody和@Controller的组合注解。*/

public class ValidateCodeServlet {

    @GetMapping("code")//是@RequestMapping(method = RequestMethod.GET)的缩写。
    public String code(HttpSession session){
        //定义图形验证码的长宽高，验证码的字符数，干扰线宽度
        /*ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(140, 35, 4, 4);*/
        //将正确的验证码保存在session作用域中
       /* String code = captcha.getCode();
        session.setAttribute("code",code);
        return code;*/

            //定义图形验证码的长、宽、验证码字符数、干扰线宽度
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(140, 60, 4, 4);

            //把正确的验证码保存在session中
            String code = captcha.getCode();
            session.setAttribute("code",code);
            //返回验证码字符串
            return code;
        }
    }


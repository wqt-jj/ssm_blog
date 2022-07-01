package com.blog.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;

@Controller
//负责页面的统一跳转
public class ViewController {
    @RequestMapping("/toView/{firstView}/{secondView}/{thirdView}")
    public String toView(
            @PathVariable("firstView") String firstView,
            @PathVariable("secondView") String secondView,
            @PathVariable("thirdView") String thirdView, HttpServletRequest request

            ){
        //获取前台所有参数名字
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = request.getParameter(name);

            //设置到作用域
            request.setAttribute(name,value);
        }

        //File.separator:代表斜杠的意思
        return firstView+ File.separator+secondView+File.separator+thirdView;
    }
}

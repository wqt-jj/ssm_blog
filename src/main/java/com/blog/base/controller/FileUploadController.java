package com.blog.base.controller;


import com.blog.back.domain.User;
import com.blog.base.util.DateTimeUtil;
import com.blog.base.util.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadController {
        //解决 Editormd文件上传
    @RequestMapping("/editorUpload")
    @ResponseBody
    public Map<String,Object> editorUpload(
            @RequestParam(value = "editormd-image-file", required = false)MultipartFile img
            , HttpSession session) {
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }

    //解决图片上传
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Map<String,Object> fileUpload(MultipartFile img, HttpSession session) {
        Map<String, Object> map = FileUploadUtil.fileUpload(img, session);
        return map;
    }

}

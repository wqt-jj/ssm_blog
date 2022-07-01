package com.blog.base.util;

import com.blog.back.domain.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//用于上传图片的工具类
public class FileUploadUtil {
    //解决 md的图片文件上传
    @RequestMapping("/editorUpload")
    @ResponseBody
    public static Map<String,Object> fileUpload(
            @RequestParam(value = "editormd-image-file", required = false)MultipartFile img
            , HttpSession session) {
        /*准备把上传的图片保存在upload目录下，还有子目录 upload/时间/用户名/很多图片*/
        //创建目录
        String realPath = session.getServletContext().getRealPath("/upload");
        //拼接时间
        realPath+= File.separator+ DateTimeUtil.getDate();
        //获取用户登录
        User user = (User) session.getAttribute("user");
        realPath+=File.separator+user.getUsername();

        File file = new File(realPath);
        //此判断是假如文件不存在
        if (!file.exists()){
            //创建带层级的目录
            file.mkdirs();
        }
        //相同用户可能会上传相同文件名的图片，防止文件重名
        //获取文件名
        String filename = img.getOriginalFilename();
        //1234476文件名.png/jpg
        filename=System.currentTimeMillis()+filename;

        //定义用于Editormd返回在页面的map数据就是把图片显示在页面来
        Map<String,Object> map=new HashMap<>();
        //获取回调回页面的地址
        String url="http://localhost/upload/"+DateTimeUtil.getDate()+
                File.separator+user.getUsername()+File.separator+filename;
        try {
            img.transferTo(new File(realPath+File.separator+filename));
            //返回success=1 url:图片回调地址（图片在服务器存储的路径）
            map.put("success",1);
            map.put("url",url);
            map.put("message","上传图片成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;

    }
}

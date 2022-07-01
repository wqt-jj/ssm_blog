package com.blog.back.controller;

import com.blog.back.domain.Article;
import com.blog.back.domain.Category;
import com.blog.back.domain.Tag;
import com.blog.back.domain.User;
import com.blog.back.service.ArticleService;
import com.blog.base.bean.ResultVo;
import com.blog.base.exception.BlogException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
public class ArticleController {
   @Autowired
    private ArticleService articleService;

    @RequestMapping("/article/list")
    @ResponseBody
    //因为需要从PageInfo里面有每页记录数所以需要用他进行返回前台
    public PageInfo<Article> list(int page, int pageSize, String title, HttpSession session){
        //文章列表为当前用户的文章
        //获取当前用户
        User user=(User) session.getAttribute("user");
        //参数一：当前页码 参数2：每页记录数
        PageHelper.startPage(page,pageSize);
        List<Article> articles=articleService.list(user.getUid(),title);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        return pageInfo;
    }
    //异步修改文章是否公开
    @RequestMapping("/article/isOpen")
    @ResponseBody
    public ResultVo isOpen(Article article){
        ResultVo resultVo = new ResultVo();
        try{
            articleService.isOpen(article);
            resultVo.setOk(true);
            if (article.getIsOpen().equals("0")){
                resultVo.setMess("文章以私密");
            }else {
                resultVo.setMess("文章以公开");
            }
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }
    //异步查询所以栏目
    @RequestMapping("/article/queryCategory")
    @ResponseBody
    public List<Category> queryCategory(){
        List<Category> categories=articleService.queryCategory();
        return categories;
    }

    //选中栏目，加载栏目下对应的标签
    @RequestMapping("/article/queryTags")
    @ResponseBody
    public List<Tag>queryTags(String cid){
        List<Tag> tags=articleService.queryTags(cid);
        return tags;
    }
    //异步发布文章
    @RequestMapping("/article/saveOrUpdate")
    @ResponseBody
    public ResultVo saveOrUpdate(Article article,HttpSession session){
        ResultVo resultVo = new ResultVo();
        try{
            //获取登录用户
            User user = (User) session.getAttribute("user");
            user.setUid(user.getUid());
            article=articleService.saveOrUpdate(article);
            resultVo.setOk(true);
            resultVo.setMess("发布文章成功");
            resultVo.setT(article);
        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }
    //异步查询文章信息
    @RequestMapping("")
    @ResponseBody
    public Article queryById(String id){
        Article article=articleService.queryById(id);
        return article;
    }

    //异步删除文章
    @RequestMapping("/article/deleteById")
    @ResponseBody
    public ResultVo deleteById(String id){
        ResultVo resultVo = new ResultVo();
        try{

            articleService.deleteById(id);
            resultVo.setOk(true);
            resultVo.setMess("删除文章成功");

        }catch (BlogException e){
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }
}

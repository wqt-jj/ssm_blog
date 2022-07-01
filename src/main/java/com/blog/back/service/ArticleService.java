package com.blog.back.service;

import com.blog.back.domain.Article;
import com.blog.back.domain.Category;
import com.blog.back.domain.Tag;

import java.util.List;

public interface ArticleService {
    //加入此参数是为按名字进行模糊查询
    List<Article> list(String uid,String title);

    void isOpen(Article article);

    List<Category> queryCategory();


    List<Tag> queryTags(String cid);

    Article saveOrUpdate(Article article);

    Article queryById(String id);

    void deleteById(String id);
}

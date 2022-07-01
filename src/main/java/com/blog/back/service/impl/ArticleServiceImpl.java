package com.blog.back.service.impl;

import com.blog.back.domain.Article;
import com.blog.back.domain.Category;
import com.blog.back.domain.Tag;
import com.blog.back.mapper.ArticleMapper;
import com.blog.back.mapper.CategoryMapper;
import com.blog.back.mapper.TagMapper;
import com.blog.back.service.ArticleService;
import com.blog.base.exception.BlogEnum;
import com.blog.base.exception.BlogException;
import com.blog.base.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
@Autowired
private ArticleMapper articleMapper;

@Autowired
private CategoryMapper categoryMapper;

@Autowired
private TagMapper tagMapper;

    public List<Article> list(String uid,String title) {
        //此两行代码是为了进行模糊查询
        Example example = new Example(Article.class);
        Example.Criteria criteria=example.createCriteria();
        //查询当前用户登录的文章
        criteria.andEqualTo("uid",uid);
        if (title!=null && !title.equals("")){
            criteria.andLike("title","%"+title+"%");
        }


        List<Article> articles = articleMapper.selectByExample(example);

        for (Article article : articles) {
            String cid = article.getCid();
            Category category = categoryMapper.selectByPrimaryKey(cid);
            if(null!=category) {
                article.setCid(category.getCname());
            }
        }
        return articles;
    }

    public void isOpen(Article article) {
        int count = articleMapper.updateByPrimaryKeySelective(article);
        if (count==0){
            throw new BlogException(BlogEnum.ARTICLE_ISOPEN);
        }
    }

    public List<Category> queryCategory() {
        return categoryMapper.selectAll();
    }

    public List<Tag> queryTags(String cid) {
        Tag tag = new Tag();
        tag.setCid(cid);
      return tagMapper.select(tag);
    }

    @Override
    public Article saveOrUpdate(Article article) {
        //点赞数
        article.setThumbsUp("0");
        //是否热门
        article.setIs_hot("0");
        //访问量
        article.setVisit_count("0");

        //发布时间
        article.setCreate_time(DateTimeUtil.getSysTime());
        //是否被评论
        article.setIsCommented("0");
        int count=articleMapper.insertSelective(article);
        if (count==0){
            //发布失败
            throw new BlogException(BlogEnum.ARTICLE_PUNISH);
        }
        return article;
    }

    @Override
    public Article queryById(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return article;
    }

    @Override
    public void deleteById(String id) {
        int count = articleMapper.deleteByPrimaryKey(id);
        if (count==0){
            throw new BlogException(BlogEnum.ARTICLE_DELETE);
        }
    }


}

//package com.blog.back.test;
//
//import com.blog.back.domain.Article;
//import com.blog.back.mapper.ArticleMapper;
//import com.blog.base.exception.BlogEnum;
//import com.blog.base.exception.BlogException;
//import org.junit.Test;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//
//public class TestBlog {
//    BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//    ArticleMapper articleMapper = (ArticleMapper) beanFactory.getBean("articleMapper");
//
//
//
//
//    //查询操作
//    @Test
//    public void test4(){
////查询一个 根据主键aid查询
//        /*Article article = new Article();
//        article.setAid("59");
//        Article article1 = articleMapper.selectOne(article);
//
//        System.out.println(article1);*/
//
//        /*进行模糊查询
//        * _:一个字符
//        * %:代表开头和结束
//        * 该查询的需求是  查询title中包含学习而且创建时间大于2021-01-24
//        * */
//        Example example = new Example(Article.class);
//        String title="学习";
//        String create_time="2021-01-24";
//       example.createCriteria().andLike("title", "%" + title + "%").
//                andGreaterThan("create_time", create_time);
////现在没有做什么处理，他的时间比较是比较字符串的长度
//        List<Article> articles = articleMapper.selectByExample(example);
//        System.out.println(articles);
//    }
//
//    //删除操作
//    @Test
//    public void test3(){
//        //此方法根据主键删除
//        //articleMapper.deleteByPrimaryKey("72");
//        /*
//        * 1.条件参数是封装在对象中
//        * 2.只能做等值的条件
//        * */
//        /*Article article = new Article();
//        article.setAid("71");
//        articleMapper.delete(article);*/
//
//
//        //删除大于等于71的
//        Example example = new Example(Article.class);
//        example.createCriteria().andGreaterThanOrEqualTo("aid", "71");
//
//        articleMapper.deleteByExample(example);
//    }
//
//    //修改操作
//    @Test
//    public void test2(){
//
//        Article article=new Article();
//        article.setAid("71");
//        article.setContent("文章内容123");
//        articleMapper.updateByPrimaryKeySelective(article);
//    }
//
//
//
//
//
//
//
////插入操作
//    @Test
//    public void test1(){
//        BeanFactory beanFactory=new ClassPathXmlApplicationContext("spring/applicationContext.xml");
//        ArticleMapper articleMapper = (ArticleMapper) beanFactory.getBean("articleMapper");
//
//        Article article=new Article();
//        article.setDigest("文章摘要");
//        article.setContent("文章内容");
//        //articleMapper.insertSelective(article);插入操作此方法推荐使用
//        articleMapper.insert(article);
//    }
//
//    //测试自定义异常
//    /*@Test
//    public void test6(){
//        int a=0;
//        try {
//                if (a==0){
//                    throw new BlogException(BlogEnum.USER_LOGIN_CODE);
//                }
//        }catch (BlogException e){
//            System.out.println(e.getMessage());
//        }
//    }*/
//
//}

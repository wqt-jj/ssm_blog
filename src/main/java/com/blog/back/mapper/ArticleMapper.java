package com.blog.back.mapper;

import com.blog.back.domain.Article;
import tk.mybatis.mapper.common.Mapper;
/*前面加了个tk.mapper这个配置所以这里继承此接口
* 下面方法也可以不用sql语句*/
public interface ArticleMapper extends Mapper<Article> {



}

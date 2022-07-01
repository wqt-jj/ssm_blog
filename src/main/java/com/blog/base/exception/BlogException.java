package com.blog.base.exception;

import jdk.jshell.spi.ExecutionControl;

//自定义运行期异常
public class BlogException extends RuntimeException {

    private BlogEnum blogEnum;

    public BlogException(BlogEnum blogEnum) {
        //从异常栈或者枚举类中获取异常信息的话
        super(blogEnum.getMessage());
        this.blogEnum = blogEnum;
    }
}

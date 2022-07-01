package com.blog.back.domain;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name ="t_tag")
@NameStyle(Style.normal)
public class Tag {
    @Id
    private String tid;
    private String tname;
    private String cid;

}

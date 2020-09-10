package com.ybj.mysql.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
@Data
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

    @ExcelIgnore
    private static final long serialVersionUID=1L;

    @ExcelProperty(value = "id",index = 0)
    @TableId("id")
    private Integer id;

    @ExcelProperty(value = "作者ID", index = 1)
    @TableField("author_id")
    private Integer authorId;

    @ExcelProperty(value = "目录ID", index = 2)
    @TableField("category_id")
    private Integer categoryId;

    @ExcelProperty(value = "观看次数", index = 3)
    @TableField("views")
    private Integer views;

    @ExcelProperty(value = "评论数",index = 4)
    @TableField("comments")
    private Integer comments;

    @ExcelProperty(value = "标题",index = 5)
    @TableField("titile")
    private String titile;

    @ExcelProperty(value = "内容",index = 6)
    @TableField("content")
    private String content;

    @ExcelIgnore
    @Version
    private Integer version;
}

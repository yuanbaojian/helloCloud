package com.ybj.mysql.dao;

import com.ybj.mysql.model.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-21
 */
public interface ArticleMapper extends BaseMapper<Article> {

    Article selectByLineLock(int id);

    Article getMaxIdArticle();

    List<Article> getCount(Integer count);
}

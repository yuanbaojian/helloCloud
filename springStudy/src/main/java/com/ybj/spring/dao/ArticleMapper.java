package com.ybj.spring.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybj.spring.model.Article;

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
}

package com.ybj.spring.service.impl;

import com.ybj.spring.dao.ArticleMapper;
import com.ybj.spring.model.Article;
import com.ybj.spring.service.ArticleServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-11-01
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleServiceI {

}

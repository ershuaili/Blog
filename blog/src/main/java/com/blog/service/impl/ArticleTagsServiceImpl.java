package com.blog.service.impl;

import com.blog.entity.ArticleTags;
import com.blog.mapper.ArticleTagsMapper;
import com.blog.service.ArticleTagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Service
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTags> implements ArticleTagsService {

}

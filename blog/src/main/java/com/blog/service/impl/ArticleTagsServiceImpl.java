package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.ArticleTagsEntity;
import com.blog.mapper.ArticleTagsMapper;
import com.blog.service.ArticleTagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章标签表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class ArticleTagsServiceImpl extends ServiceImpl<ArticleTagsMapper, ArticleTagsEntity> implements ArticleTagsService {
    @Resource
    private ArticleTagsMapper articleTagsMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<ArticleTagsEntity> getConditionsByEntity(ArticleTagsEntity param) {
        QueryWrapper<ArticleTagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, ArticleTagsEntity::getId, param.getId())
                // 标签表主键
                .eq(param.getTagId() != null, ArticleTagsEntity::getTagId, param.getTagId())
                // 文章ID
                .eq(param.getArticleId() != null, ArticleTagsEntity::getArticleId, param.getArticleId())
                // 添加时间
                .eq(param.getCreateTime() != null, ArticleTagsEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, ArticleTagsEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.ArticleEntity;
import com.blog.mapper.ArticleMapper;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleEntity> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<ArticleEntity> getConditionsByEntity(ArticleEntity param) {
        QueryWrapper<ArticleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, ArticleEntity::getId, param.getId())
                // 文章标题
                .eq(!StringUtils.isEmpty(param.getTitle()), ArticleEntity::getTitle, param.getTitle())
                // 用户ID
                .eq(param.getUserId() != null, ArticleEntity::getUserId, param.getUserId())
                // 文章封面图片
                .eq(!StringUtils.isEmpty(param.getCoverImage()), ArticleEntity::getCoverImage, param.getCoverImage())
                // 当前文章适用编辑器类型
                .eq(!StringUtils.isEmpty(param.getEditorType()), ArticleEntity::getEditorType, param.getEditorType())
                // 是否是markdown格式
                .eq(param.getIsMarkdown() != null, ArticleEntity::getIsMarkdown, param.getIsMarkdown())
                // 文章内容
                .eq(!StringUtils.isEmpty(param.getContent()), ArticleEntity::getContent, param.getContent())
                // markdown版文章内容
                .eq(!StringUtils.isEmpty(param.getContentMd()), ArticleEntity::getContentMd, param.getContentMd())
                // 是否置顶
                .eq(param.getTop() != null, ArticleEntity::getTop, param.getTop())
                // 类型
                .eq(param.getTypeId() != null, ArticleEntity::getTypeId, param.getTypeId())
                // 状态
                .eq(param.getStatus() != null, ArticleEntity::getStatus, param.getStatus())
                // 是否推荐
                .eq(param.getRecommended() != null, ArticleEntity::getRecommended, param.getRecommended())
                // 是否原创
                .eq(param.getOriginal() != null, ArticleEntity::getOriginal, param.getOriginal())
                // 文章简介，最多200字
                .eq(!StringUtils.isEmpty(param.getDescription()), ArticleEntity::getDescription, param.getDescription())
                // 文章关键字，优化搜索
                .eq(!StringUtils.isEmpty(param.getKeywords()), ArticleEntity::getKeywords, param.getKeywords())
                // 是否开启评论
                .eq(param.getComment() != null, ArticleEntity::getComment, param.getComment())
                // 文章私密访问时密钥
                .eq(!StringUtils.isEmpty(param.getPassword()), ArticleEntity::getPassword, param.getPassword())
                // 该文章是否登录后才可访问
                .eq(param.getRequiredAuth() != null, ArticleEntity::getRequiredAuth, param.getRequiredAuth())
                // 添加时间
                .eq(param.getCreateTime() != null, ArticleEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, ArticleEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

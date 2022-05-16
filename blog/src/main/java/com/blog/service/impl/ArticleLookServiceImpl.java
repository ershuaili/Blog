package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.ArticleLookEntity;
import com.blog.mapper.ArticleLookMapper;
import com.blog.service.ArticleLookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户浏览记录表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class ArticleLookServiceImpl extends ServiceImpl<ArticleLookMapper, ArticleLookEntity> implements ArticleLookService {
    @Resource
    private ArticleLookMapper articleLookMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<ArticleLookEntity> getConditionsByEntity(ArticleLookEntity param) {
        QueryWrapper<ArticleLookEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, ArticleLookEntity::getId, param.getId())
                // 文章ID
                .eq(param.getArticleId() != null, ArticleLookEntity::getArticleId, param.getArticleId())
                // 已登录用户ID
                .eq(param.getUserId() != null, ArticleLookEntity::getUserId, param.getUserId())
                // 用户IP
                .eq(!StringUtils.isEmpty(param.getUserIp()), ArticleLookEntity::getUserIp, param.getUserIp())
                // 浏览时间
                .eq(param.getLookTime() != null, ArticleLookEntity::getLookTime, param.getLookTime())
                // 添加时间
                .eq(param.getCreateTime() != null, ArticleLookEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, ArticleLookEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

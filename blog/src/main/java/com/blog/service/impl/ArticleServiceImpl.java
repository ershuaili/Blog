package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.article.ArticleEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.ArticleMapper;
import com.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 文章新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ArticleEntity param) {
        if (articleMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 文章修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ArticleEntity param) {
        if (articleMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 文章删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (articleMapper.deleteById(id) == 0) {
            throw new BusinessException(BusinessErrorCodes.DELETE_FAILED);
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    @Override
    public ArticleEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return articleMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<ArticleEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<ArticleEntity> entities = articleMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<ArticleEntity> listByConditions(ArticleEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<ArticleEntity> queryWrapper = getConditionsByEntity(conditions);

        List<ArticleEntity> entities = articleMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

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

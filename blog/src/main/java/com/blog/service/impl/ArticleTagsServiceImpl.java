package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.entity.article.ArticleTagsEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.ArticleTagsMapper;
import com.blog.service.ArticleTagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 文章标签 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class ArticleTagsServiceImpl implements ArticleTagsService {

    @Resource
    private ArticleTagsMapper articleTagsMapper;

    /**
     * 文章标签新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ArticleTagsEntity param) {
        if (articleTagsMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 文章标签修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ArticleTagsEntity param) {
        if (articleTagsMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 文章标签删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (articleTagsMapper.deleteById(id) == 0) {
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
    public ArticleTagsEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return articleTagsMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<ArticleTagsEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<ArticleTagsEntity> entities = articleTagsMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<ArticleTagsEntity> listByConditions(ArticleTagsEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<ArticleTagsEntity> queryWrapper = getConditionsByEntity(conditions);

        List<ArticleTagsEntity> entities = articleTagsMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

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

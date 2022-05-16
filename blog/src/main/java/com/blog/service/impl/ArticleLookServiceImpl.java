package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.ArticleLookEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.ArticleLookMapper;
import com.blog.service.ArticleLookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户浏览记录 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class ArticleLookServiceImpl implements ArticleLookService {

    @Resource
    private ArticleLookMapper articleLookMapper;

    /**
     * 用户浏览记录新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ArticleLookEntity param) {
        if (articleLookMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户浏览记录修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(ArticleLookEntity param) {
        if (articleLookMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户浏览记录删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (articleLookMapper.deleteById(id) == 0) {
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
    public ArticleLookEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return articleLookMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<ArticleLookEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<ArticleLookEntity> entities = articleLookMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<ArticleLookEntity> listByConditions(ArticleLookEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<ArticleLookEntity> queryWrapper = getConditionsByEntity(conditions);

        List<ArticleLookEntity> entities = articleLookMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

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

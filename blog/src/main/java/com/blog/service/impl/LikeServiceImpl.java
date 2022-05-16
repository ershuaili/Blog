package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.LikeEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.LikeMapper;
import com.blog.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 点赞 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class LikeServiceImpl implements LikeService {

    @Resource
    private LikeMapper likeMapper;

    /**
     * 点赞新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(LikeEntity param) {
        if (likeMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 点赞修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(LikeEntity param) {
        if (likeMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 点赞删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (likeMapper.deleteById(id) == 0) {
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
    public LikeEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return likeMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<LikeEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<LikeEntity> entities = likeMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<LikeEntity> listByConditions(LikeEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<LikeEntity> queryWrapper = getConditionsByEntity(conditions);

        List<LikeEntity> entities = likeMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<LikeEntity> getConditionsByEntity(LikeEntity param) {
        QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, LikeEntity::getId, param.getId())
                // 业务模块编号: 0-博客，1-评论，2-留言，3-图片
                .eq(param.getModuleCode() != null, LikeEntity::getModuleCode, param.getModuleCode())
                // 业务数据主键id
                .eq(param.getBusinessId() != null, LikeEntity::getBusinessId, param.getBusinessId())
                // 操作用户id
                .eq(param.getUserId() != null, LikeEntity::getUserId, param.getUserId())
                // 操作类型: 0-点赞，1-点踩
                .eq(param.getOperationType() != null, LikeEntity::getOperationType, param.getOperationType())
                // 操作备注
                .eq(!StringUtils.isEmpty(param.getRemark()), LikeEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

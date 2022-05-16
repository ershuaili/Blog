package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.TypeEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 文章类型 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class TypeServiceImpl implements TypeService {

    @Resource
    private TypeMapper typeMapper;

    /**
     * 文章类型新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(TypeEntity param) {
        if (typeMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 文章类型修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(TypeEntity param) {
        if (typeMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 文章类型删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (typeMapper.deleteById(id) == 0) {
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
    public TypeEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return typeMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<TypeEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<TypeEntity> entities = typeMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<TypeEntity> listByConditions(TypeEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<TypeEntity> queryWrapper = getConditionsByEntity(conditions);

        List<TypeEntity> entities = typeMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<TypeEntity> getConditionsByEntity(TypeEntity param) {
        QueryWrapper<TypeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, TypeEntity::getId, param.getId())
                // 文章类型名
                .eq(!StringUtils.isEmpty(param.getName()), TypeEntity::getName, param.getName())
                // 类型介绍
                .eq(!StringUtils.isEmpty(param.getDescription()), TypeEntity::getDescription, param.getDescription())
                // 排序
                .eq(param.getSort() != null, TypeEntity::getSort, param.getSort())
                // 图标
                .eq(!StringUtils.isEmpty(param.getIcon()), TypeEntity::getIcon, param.getIcon())
                // 删除标记
                .eq(param.getDeleteFlag() != null, TypeEntity::getDeleteFlag, param.getDeleteFlag())
                // 添加时间
                .eq(param.getCreateTime() != null, TypeEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, TypeEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

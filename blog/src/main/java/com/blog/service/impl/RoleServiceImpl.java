package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.RoleEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.RoleMapper;
import com.blog.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    /**
     * 角色新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RoleEntity param) {
        if (roleMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 角色修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(RoleEntity param) {
        if (roleMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 角色删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (roleMapper.deleteById(id) == 0) {
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
    public RoleEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return roleMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<RoleEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<RoleEntity> entities = roleMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<RoleEntity> listByConditions(RoleEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<RoleEntity> queryWrapper = getConditionsByEntity(conditions);

        List<RoleEntity> entities = roleMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<RoleEntity> getConditionsByEntity(RoleEntity param) {
        QueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 自增主键
                .eq(param.getId() != null, RoleEntity::getId, param.getId())
                // 角色名
                .eq(!StringUtils.isEmpty(param.getName()), RoleEntity::getName, param.getName())
                // 描述信息
                .eq(!StringUtils.isEmpty(param.getDescription()), RoleEntity::getDescription, param.getDescription())
                // 创建人主键
                .eq(param.getCreateId() != null, RoleEntity::getCreateId, param.getCreateId())
                // 创建时间
                .eq(param.getCreateTime() != null, RoleEntity::getCreateTime, param.getCreateTime())
                // 更新人主键
                .eq(param.getUpdateId() != null, RoleEntity::getUpdateId, param.getUpdateId())
                // 更新时间
                .eq(param.getUpdateTime() != null, RoleEntity::getUpdateTime, param.getUpdateTime())
                // 删除标记
                .eq(param.getDeleteFlag() != null, RoleEntity::getDeleteFlag, param.getDeleteFlag())
                // 是否有效
                .eq(param.getAvailable() != null, RoleEntity::getAvailable, param.getAvailable())
        ;
        return queryWrapper;
    }

}

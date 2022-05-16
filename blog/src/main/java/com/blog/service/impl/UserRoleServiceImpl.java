package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blog.entity.UserRoleEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.UserRoleMapper;
import com.blog.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 用户角色新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserRoleEntity param) {
        if (userRoleMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户角色修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UserRoleEntity param) {
        if (userRoleMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户角色删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (userRoleMapper.deleteById(id) == 0) {
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
    public UserRoleEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return userRoleMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<UserRoleEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<UserRoleEntity> entities = userRoleMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<UserRoleEntity> listByConditions(UserRoleEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<UserRoleEntity> queryWrapper = getConditionsByEntity(conditions);

        List<UserRoleEntity> entities = userRoleMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UserRoleEntity> getConditionsByEntity(UserRoleEntity param) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, UserRoleEntity::getId, param.getId())
                //
                .eq(param.getUserId() != null, UserRoleEntity::getUserId, param.getUserId())
                //
                .eq(param.getRoleId() != null, UserRoleEntity::getRoleId, param.getRoleId())
                // 添加时间
                .eq(param.getCreateTime() != null, UserRoleEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, UserRoleEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

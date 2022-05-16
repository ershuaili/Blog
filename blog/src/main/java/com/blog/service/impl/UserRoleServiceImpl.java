package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.UserRoleEntity;
import com.blog.mapper.UserRoleMapper;
import com.blog.service.UserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {
    @Resource
    private UserRoleMapper userRoleMapper;


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

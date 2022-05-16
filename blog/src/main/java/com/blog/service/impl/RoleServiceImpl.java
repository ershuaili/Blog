package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.RoleEntity;
import com.blog.mapper.RoleMapper;
import com.blog.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    @Resource
    private RoleMapper roleMapper;


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

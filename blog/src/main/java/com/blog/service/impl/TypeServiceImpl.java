package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.TypeEntity;
import com.blog.mapper.TypeMapper;
import com.blog.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 文章类型表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, TypeEntity> implements TypeService {
    @Resource
    private TypeMapper typeMapper;


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

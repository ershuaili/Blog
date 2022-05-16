package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.TagsEntity;
import com.blog.mapper.TagsMapper;
import com.blog.service.TagsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 标签表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, TagsEntity> implements TagsService {
    @Resource
    private TagsMapper tagsMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<TagsEntity> getConditionsByEntity(TagsEntity param) {
        QueryWrapper<TagsEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, TagsEntity::getId, param.getId())
                // 标签名
                .eq(!StringUtils.isEmpty(param.getName()), TagsEntity::getName, param.getName())
                // 描述
                .eq(!StringUtils.isEmpty(param.getDescription()), TagsEntity::getDescription, param.getDescription())
                // 添加时间
                .eq(param.getCreateTime() != null, TagsEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, TagsEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

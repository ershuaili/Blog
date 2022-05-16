package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.UpdateRecordeEntity;
import com.blog.mapper.UpdateRecordeMapper;
import com.blog.service.UpdateRecordeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 项目更新记录表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class UpdateRecordeServiceImpl extends ServiceImpl<UpdateRecordeMapper, UpdateRecordeEntity> implements UpdateRecordeService {
    @Resource
    private UpdateRecordeMapper updateRecordeMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UpdateRecordeEntity> getConditionsByEntity(UpdateRecordeEntity param) {
        QueryWrapper<UpdateRecordeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, UpdateRecordeEntity::getId, param.getId())
                // 更新版本
                .eq(!StringUtils.isEmpty(param.getVersion()), UpdateRecordeEntity::getVersion, param.getVersion())
                // 更新记录备注
                .eq(!StringUtils.isEmpty(param.getDescription()), UpdateRecordeEntity::getDescription, param.getDescription())
                // 项目更新时间
                .eq(param.getRecordeTime() != null, UpdateRecordeEntity::getRecordeTime, param.getRecordeTime())
                // 添加时间
                .eq(param.getCreateTime() != null, UpdateRecordeEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, UpdateRecordeEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

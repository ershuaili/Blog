package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.DataChangeLogEntity;
import com.blog.mapper.DataChangeLogMapper;
import com.blog.service.DataChangeLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 数据变更记录 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class DataChangeLogServiceImpl extends ServiceImpl<DataChangeLogMapper, DataChangeLogEntity> implements DataChangeLogService {
    @Resource
    private DataChangeLogMapper dataChangeLogMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<DataChangeLogEntity> getConditionsByEntity(DataChangeLogEntity param) {
        QueryWrapper<DataChangeLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, DataChangeLogEntity::getId, param.getId())
                // 数据变更记录类型. 0-基本数据变更, 1-状态数据变更
                .eq(param.getChangeType() != null, DataChangeLogEntity::getChangeType, param.getChangeType())
                // 业务模块编号
                .eq(!StringUtils.isEmpty(param.getModuleCode()), DataChangeLogEntity::getModuleCode, param.getModuleCode())
                // 业务数据主键id
                .eq(param.getBusinessId() != null, DataChangeLogEntity::getBusinessId, param.getBusinessId())
                // 记录创建人id
                .eq(param.getCreateId() != null, DataChangeLogEntity::getCreateId, param.getCreateId())
                // 记录创建时间
                .eq(param.getCreateTime() != null, DataChangeLogEntity::getCreateTime, param.getCreateTime())
                // 数据变动内容
                .eq(!StringUtils.isEmpty(param.getDataDiff()), DataChangeLogEntity::getDataDiff, param.getDataDiff())
                // 操作理由
                .eq(!StringUtils.isEmpty(param.getReason()), DataChangeLogEntity::getReason, param.getReason())
                // 操作备注
                .eq(!StringUtils.isEmpty(param.getRemark()), DataChangeLogEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

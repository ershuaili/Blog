package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.sys.DataChangeLogEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.DataChangeLogMapper;
import com.blog.service.DataChangeLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 数据变更记录 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class DataChangeLogServiceImpl implements DataChangeLogService {

    @Resource
    private DataChangeLogMapper dataChangeLogMapper;

    /**
     * 数据变更记录新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(DataChangeLogEntity param) {
        if (dataChangeLogMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 数据变更记录修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(DataChangeLogEntity param) {
        if (dataChangeLogMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 数据变更记录删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (dataChangeLogMapper.deleteById(id) == 0) {
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
    public DataChangeLogEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return dataChangeLogMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<DataChangeLogEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<DataChangeLogEntity> entities = dataChangeLogMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<DataChangeLogEntity> listByConditions(DataChangeLogEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<DataChangeLogEntity> queryWrapper = getConditionsByEntity(conditions);

        List<DataChangeLogEntity> entities = dataChangeLogMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

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

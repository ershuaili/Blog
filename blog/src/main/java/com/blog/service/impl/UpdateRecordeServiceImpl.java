package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.sys.UpdateRecordeEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.UpdateRecordeMapper;
import com.blog.service.UpdateRecordeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 项目更新记录 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class UpdateRecordeServiceImpl implements UpdateRecordeService {

    @Resource
    private UpdateRecordeMapper updateRecordeMapper;

    /**
     * 项目更新记录新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UpdateRecordeEntity param) {
        if (updateRecordeMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 项目更新记录修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UpdateRecordeEntity param) {
        if (updateRecordeMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 项目更新记录删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (updateRecordeMapper.deleteById(id) == 0) {
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
    public UpdateRecordeEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return updateRecordeMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<UpdateRecordeEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<UpdateRecordeEntity> entities = updateRecordeMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<UpdateRecordeEntity> listByConditions(UpdateRecordeEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<UpdateRecordeEntity> queryWrapper = getConditionsByEntity(conditions);

        List<UpdateRecordeEntity> entities = updateRecordeMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

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

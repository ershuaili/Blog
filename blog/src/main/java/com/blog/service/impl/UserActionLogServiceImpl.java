package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.UserActionLogEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.UserActionLogMapper;
import com.blog.service.UserActionLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户操作日志 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class UserActionLogServiceImpl implements UserActionLogService {

    @Resource
    private UserActionLogMapper userActionLogMapper;

    /**
     * 用户操作日志新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserActionLogEntity param) {
        if (userActionLogMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户操作日志修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UserActionLogEntity param) {
        if (userActionLogMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户操作日志删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (userActionLogMapper.deleteById(id) == 0) {
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
    public UserActionLogEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return userActionLogMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<UserActionLogEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<UserActionLogEntity> entities = userActionLogMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<UserActionLogEntity> listByConditions(UserActionLogEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<UserActionLogEntity> queryWrapper = getConditionsByEntity(conditions);

        List<UserActionLogEntity> entities = userActionLogMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UserActionLogEntity> getConditionsByEntity(UserActionLogEntity param) {
        QueryWrapper<UserActionLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, UserActionLogEntity::getId, param.getId())
                // 请求链接
                .eq(!StringUtils.isEmpty(param.getUrl()), UserActionLogEntity::getUrl, param.getUrl())
                // 操作名
                .eq(!StringUtils.isEmpty(param.getActionName()), UserActionLogEntity::getActionName, param.getActionName())
                // 提交内容（json）
                .eq(!StringUtils.isEmpty(param.getParams()), UserActionLogEntity::getParams, param.getParams())
                // 操作人
                .eq(param.getUserAccountId() != null, UserActionLogEntity::getUserAccountId, param.getUserAccountId())
                // 操作时间
                .eq(param.getCreateTime() != null, UserActionLogEntity::getCreateTime, param.getCreateTime())
        ;
        return queryWrapper;
    }

}

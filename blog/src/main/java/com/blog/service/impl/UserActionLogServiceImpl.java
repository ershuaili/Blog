package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.UserActionLogEntity;
import com.blog.mapper.UserActionLogMapper;
import com.blog.service.UserActionLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户操作日志 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class UserActionLogServiceImpl extends ServiceImpl<UserActionLogMapper, UserActionLogEntity> implements UserActionLogService {
    @Resource
    private UserActionLogMapper userActionLogMapper;


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

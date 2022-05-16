package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.UserEntity;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Resource
    private UserMapper userMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UserEntity> getConditionsByEntity(UserEntity param) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, UserEntity::getId, param.getId())
                // 用户名
                .eq(!StringUtils.isEmpty(param.getName()), UserEntity::getName, param.getName())
                // 用户昵称
                .eq(!StringUtils.isEmpty(param.getNickname()), UserEntity::getNickname, param.getNickname())
                // 手机号
                .eq(!StringUtils.isEmpty(param.getMobile()), UserEntity::getMobile, param.getMobile())
                // 邮箱
                .eq(!StringUtils.isEmpty(param.getMail()), UserEntity::getMail, param.getMail())
                // 头像图片
                .eq(!StringUtils.isEmpty(param.getAvatar()), UserEntity::getAvatar, param.getAvatar())
                // 生日
                .eq(param.getBirthday() != null, UserEntity::getBirthday, param.getBirthday())
                // 性别: 0-男，1-女
                .eq(param.getGender() != null, UserEntity::getGender, param.getGender())
                // 密文密码
                .eq(!StringUtils.isEmpty(param.getPassword()), UserEntity::getPassword, param.getPassword())
                // 注册时间
                .eq(param.getCreateTime() != null, UserEntity::getCreateTime, param.getCreateTime())
                // 注册IP
                .eq(!StringUtils.isEmpty(param.getCreateIp()), UserEntity::getCreateIp, param.getCreateIp())
                // 最近登录IP
                .eq(!StringUtils.isEmpty(param.getLastLoginIp()), UserEntity::getLastLoginIp, param.getLastLoginIp())
                // 最近登录时间
                .eq(param.getLastLoginTime() != null, UserEntity::getLastLoginTime, param.getLastLoginTime())
                // 登录次数
                .eq(param.getLoginCount() != null, UserEntity::getLoginCount, param.getLoginCount())
                // 更新时间
                .eq(param.getUpdateTime() != null, UserEntity::getUpdateTime, param.getUpdateTime())
                // 用户状态: 0无效，1有效
                .eq(param.getStatus() != null, UserEntity::getStatus, param.getStatus())
                // 删除标记
                .eq(param.getDeleteFlag() != null, UserEntity::getDeleteFlag, param.getDeleteFlag())
                // 语言编号
                .eq(!StringUtils.isEmpty(param.getLanguageCode()), UserEntity::getLanguageCode, param.getLanguageCode())
                // 用户备注
                .eq(!StringUtils.isEmpty(param.getRemark()), UserEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

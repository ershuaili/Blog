package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.user.UserAuthEntity;
import com.blog.entity.user.UserEntity;
import com.blog.entity.user.UserRoleEntity;
import com.blog.mapper.RoleMapper;
import com.blog.mapper.UserMapper;
import com.blog.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (MyUserDetails)表服务实现类
 *
 * @author 李二帅
 * @since 2021-10-28 12:28:01
 */
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * 从数据库查询用户
     *
     * @param username 用户名
     * @return 用户
     * @throws UsernameNotFoundException 用户名未找到
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户信息
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(username), UserEntity::getName, username);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        UserAuthEntity userAuthEntity = new UserAuthEntity();
        BeanUtils.copyProperties(userEntity, userAuthEntity);
        // 用户角色列表
        LambdaQueryWrapper<UserRoleEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userEntity.getId() != null, UserRoleEntity::getUserId, userEntity.getId());
        List<UserRoleEntity> userRoleEntities = userRoleMapper.selectList(wrapper);
        // 根据角色id查询角色信息
        List<Long> collect = userRoleEntities.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList());
        userAuthEntity.setRoles(roleMapper.selectBatchIds(collect));

        if (ObjectUtils.isEmpty(userAuthEntity)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        } else {
            return userAuthEntity;
        }
    }
}

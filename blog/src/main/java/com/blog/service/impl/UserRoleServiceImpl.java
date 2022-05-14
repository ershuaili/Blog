package com.blog.service.impl;

import com.blog.entity.UserRole;
import com.blog.mapper.UserRoleMapper;
import com.blog.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

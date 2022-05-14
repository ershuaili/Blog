package com.blog.service.impl;

import com.blog.entity.Role;
import com.blog.mapper.RoleMapper;
import com.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

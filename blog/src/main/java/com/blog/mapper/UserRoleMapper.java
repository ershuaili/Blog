package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.user.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}

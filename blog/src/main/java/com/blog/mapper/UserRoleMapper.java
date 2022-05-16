package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

}

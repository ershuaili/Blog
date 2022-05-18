package com.blog.service;

import com.blog.entity.user.UserRoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface UserRoleService {

    /**
     * 用户角色新增
     *
     * @param param 根据需要进行传值
     */
    void add(UserRoleEntity param);

    /**
     * 用户角色修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(UserRoleEntity param);

    /**
     * 用户角色删除(单个条目)
     *
     * @param id-id
     */
    void removeById(Long id);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    UserRoleEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<UserRoleEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<UserRoleEntity> listByConditions(UserRoleEntity conditions);

}

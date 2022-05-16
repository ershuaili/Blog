package com.blog.service;

import com.blog.entity.UserEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface UserService {

    /**
     * 用户信息新增
     *
     * @param param 根据需要进行传值
     */
    void add(UserEntity param);

    /**
     * 用户信息修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(UserEntity param);

    /**
     * 用户信息删除(单个条目)
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
    UserEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<UserEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<UserEntity> listByConditions(UserEntity conditions);

}

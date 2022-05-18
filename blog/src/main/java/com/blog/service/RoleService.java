package com.blog.service;

import com.blog.entity.sys.RoleEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface RoleService {

    /**
     * 角色新增
     *
     * @param param 根据需要进行传值
     */
    void add(RoleEntity param);

    /**
     * 角色修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(RoleEntity param);

    /**
     * 角色删除(单个条目)
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
    RoleEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<RoleEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<RoleEntity> listByConditions(RoleEntity conditions);

}

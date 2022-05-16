package com.blog.service;

import com.blog.entity.UserActionLogEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户操作日志 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface UserActionLogService {

    /**
     * 用户操作日志新增
     *
     * @param param 根据需要进行传值
     */
    void add(UserActionLogEntity param);

    /**
     * 用户操作日志修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(UserActionLogEntity param);

    /**
     * 用户操作日志删除(单个条目)
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
    UserActionLogEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<UserActionLogEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<UserActionLogEntity> listByConditions(UserActionLogEntity conditions);

}

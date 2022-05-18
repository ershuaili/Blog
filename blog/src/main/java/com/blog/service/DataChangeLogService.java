package com.blog.service;

import com.blog.entity.sys.DataChangeLogEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 数据变更记录 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface DataChangeLogService {

    /**
     * 数据变更记录新增
     *
     * @param param 根据需要进行传值
     */
    void add(DataChangeLogEntity param);

    /**
     * 数据变更记录修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(DataChangeLogEntity param);

    /**
     * 数据变更记录删除(单个条目)
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
    DataChangeLogEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<DataChangeLogEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<DataChangeLogEntity> listByConditions(DataChangeLogEntity conditions);

}

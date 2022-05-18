package com.blog.service;

import com.blog.entity.sys.UpdateRecordeEntity;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 项目更新记录 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface UpdateRecordeService {

    /**
     * 项目更新记录新增
     *
     * @param param 根据需要进行传值
     */
    void add(UpdateRecordeEntity param);

    /**
     * 项目更新记录修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(UpdateRecordeEntity param);

    /**
     * 项目更新记录删除(单个条目)
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
    UpdateRecordeEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<UpdateRecordeEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<UpdateRecordeEntity> listByConditions(UpdateRecordeEntity conditions);

}

package com.blog.mapper;

import com.blog.entity.DataChangeLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据变更记录 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Mapper
public interface DataChangeLogMapper extends BaseMapper<DataChangeLog> {

}

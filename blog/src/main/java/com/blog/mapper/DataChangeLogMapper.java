package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.sys.DataChangeLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 数据变更记录 Mapper 接口
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Mapper
public interface DataChangeLogMapper extends BaseMapper<DataChangeLogEntity> {

}

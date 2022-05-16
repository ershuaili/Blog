package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.TypeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章类型表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface TypeMapper extends BaseMapper<TypeEntity> {

}

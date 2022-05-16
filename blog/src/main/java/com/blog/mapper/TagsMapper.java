package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.TagsEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface TagsMapper extends BaseMapper<TagsEntity> {

}

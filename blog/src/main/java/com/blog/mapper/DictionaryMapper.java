package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.DictionaryEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<DictionaryEntity> {

}

package com.blog.mapper;

import com.blog.entity.Dictionary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

}

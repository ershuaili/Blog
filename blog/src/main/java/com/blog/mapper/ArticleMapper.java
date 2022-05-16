package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.ArticleEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleEntity> {

}

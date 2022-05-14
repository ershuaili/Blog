package com.blog.mapper;

import com.blog.entity.ArticleTags;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 文章标签表 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Mapper
public interface ArticleTagsMapper extends BaseMapper<ArticleTags> {

}

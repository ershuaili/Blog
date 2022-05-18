package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.article.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户评论 Mapper 接口
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}

package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户评论表 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {

}

package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.dto.CommentDTO;
import com.blog.entity.article.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 查询所有一级评论
     * @param current 当前页
     * @param size 每页数量
     * @param articleId 文章id
     * @return 评论集合
     */
    List<CommentDTO> selectCommentList(Long current, Long size,Long articleId);

}

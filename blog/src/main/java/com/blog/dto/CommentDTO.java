package com.blog.dto;

import com.blog.entity.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author 委稼祥
 * @description : 评论
 * </p>
 * @since : 2022-07-26 13:46
 * <p>
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO extends BaseEntity {

    /**
     * 评论id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 文章id
     */
    private Long articleId;

    /**
     * 父id
     */
    private Long Pid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 点赞数
     */
    private Integer support;

    /**
     * 点踩数
     */
    private Integer oppose;

    /**
     * 评论时间
     */
    private LocalDateTime createTime;
}

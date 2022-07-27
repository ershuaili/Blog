package com.blog.vo;

import com.blog.entity.BaseEntity;
import com.blog.entity.article.CommentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 用户评论
 * </p>
 *
 * @author 李二帅
 * @since 2022-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CommentEntity对象", description = "用户评论")
public class CommentVO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("被评论文章ID")
    private Long articleId;

    @ApiModelProperty("评论人ID")
    private Long userId;

    @ApiModelProperty("评论人名称")
    private Long userName;

    @ApiModelProperty("父评论ID")
    private Long parentId;

    @ApiModelProperty("评论时IP")
    private String userIp;

    @ApiModelProperty("评论时系统类型")
    private String userOs;

    @ApiModelProperty("评论时浏览器类型")
    private String browser;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("支持（赞）")
    private Long support;

    @ApiModelProperty("反对（踩）")
    private Long oppose;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("子评论")
    private List<CommentEntity> childComment;
}

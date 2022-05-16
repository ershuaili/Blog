package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户评论
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@TableName("biz_comment")
@ApiModel(value = "CommentEntity对象", description = "用户评论")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("被评论文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("评论人ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("父级评论id")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty("评论时ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("评论时系统类型")
    @TableField("os")
    private String os;

    @ApiModelProperty("评论时浏览器类型")
    @TableField("browser")
    private String browser;

    @ApiModelProperty("评论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("支持（赞）")
    @TableField("support")
    private Integer support;

    @ApiModelProperty("反对（踩）")
    @TableField("oppose")
    private Integer oppose;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

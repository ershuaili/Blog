package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户评论表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Getter
@Setter
@TableName("biz_comment")
@ApiModel(value = "Comment对象", description = "用户评论表")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("被评论的文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("评论人的ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("父级评论的id")
    @TableField("pid")
    private Long pid;

    @ApiModelProperty("评论时的ip")
    @TableField("ip")
    private String ip;

    @ApiModelProperty("评论时的系统类型")
    @TableField("os")
    private String os;

    @ApiModelProperty("评论时的浏览器类型")
    @TableField("browser")
    private String browser;

    @ApiModelProperty("评论的内容")
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

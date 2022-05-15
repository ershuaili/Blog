package com.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 文章表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Data
@TableName("biz_article")
@ApiModel(value = "Article对象", description = "文章表")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文章标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("文章封面图片")
    @TableField("cover_image")
    private String coverImage;

    @ApiModelProperty("当前文章适用编辑器类型")
    @TableField("editor_type")
    private String editorType;

    @ApiModelProperty("是否是markdown格式")
    @TableField("is_markdown")
    private Integer isMarkdown;

    @ApiModelProperty("文章内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("markdown版文章内容")
    @TableField("content_md")
    private String contentMd;

    @ApiModelProperty("是否置顶")
    @TableField("top")
    private Boolean top;

    @ApiModelProperty("类型")
    @TableField("type_id")
    private Long typeId;

    @ApiModelProperty("状态")
    @TableField("status")
    @TableLogic
    private Integer status;

    @ApiModelProperty("是否推荐")
    @TableField("recommended")
    private Integer recommended;

    @ApiModelProperty("是否原创")
    @TableField("original")
    private Integer original;

    @ApiModelProperty("文章简介，最多200字")
    @TableField("description")
    private String description;

    @ApiModelProperty("文章关键字，优化搜索")
    @TableField("keywords")
    private String keywords;

    @ApiModelProperty("是否开启评论")
    @TableField("comment")
    private Integer comment;

    @ApiModelProperty("文章私密访问时密钥")
    @TableField("password")
    private String password;

    @ApiModelProperty("该文章是否登录后才可访问")
    @TableField("required_auth")
    private Integer requiredAuth;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

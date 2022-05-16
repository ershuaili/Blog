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
 * 用户浏览记录
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@TableName("biz_article_look")
@ApiModel(value = "ArticleLookEntity对象", description = "用户浏览记录")
public class ArticleLookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("已登录用户ID")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("用户IP")
    @TableField("user_ip")
    private String userIp;

    @ApiModelProperty("浏览时间")
    @TableField("look_time")
    private LocalDateTime lookTime;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

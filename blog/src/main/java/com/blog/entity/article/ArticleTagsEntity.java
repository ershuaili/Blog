package com.blog.entity.article;

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
 * 文章标签
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@TableName("biz_article_tags")
@ApiModel(value = "ArticleTagsEntity对象", description = "文章标签")
public class ArticleTagsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标签表主键")
    @TableField("tag_id")
    private Long tagId;

    @ApiModelProperty("文章ID")
    @TableField("article_id")
    private Long articleId;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

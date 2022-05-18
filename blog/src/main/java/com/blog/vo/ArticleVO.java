package com.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@ApiModel(value = "文章VO", description = "文章VO")
public class ArticleVO {

    @ApiModelProperty("文章标题")
    private String title;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("文章封面图片")
    private String coverImage;

    @ApiModelProperty("是否置顶")
    private Boolean top;

    @ApiModelProperty("类型")
    private Long typeId;

    @ApiModelProperty("是否推荐")
    private Integer recommended;

    @ApiModelProperty("是否原创")
    private Integer original;

    @ApiModelProperty("文章简介，最多200字")
    private String description;

    @ApiModelProperty("文章关键字，优化搜索")
    private String keywords;

    @ApiModelProperty("是否开启评论")
    private Integer comment;

}

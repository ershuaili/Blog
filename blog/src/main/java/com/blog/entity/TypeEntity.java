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
 * 文章类型表
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Data
@TableName("biz_type")
@ApiModel(value = "TypeEntity对象", description = "文章类型表")
public class TypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("文章类型名")
    @TableField("name")
    private String name;

    @ApiModelProperty("类型介绍")
    @TableField("description")
    private String description;

    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("删除标记")
    @TableField("delete_flag")
    private Boolean deleteFlag;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

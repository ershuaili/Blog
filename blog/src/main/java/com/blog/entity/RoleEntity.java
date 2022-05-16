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
 * 角色
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Data
@TableName("biz_role")
@ApiModel(value = "RoleEntity对象", description = "角色")
public class RoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("角色名")
    @TableField("name")
    private String name;

    @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建人主键")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新人主键")
    @TableField("update_id")
    private Long updateId;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("删除标记")
    @TableField("delete_flag")
    private Boolean deleteFlag;

    @ApiModelProperty("是否有效")
    @TableField("available")
    private Boolean available;


}

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
 * 项目更新记录表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Getter
@Setter
@TableName("biz_update_recorde")
@ApiModel(value = "UpdateRecorde对象", description = "项目更新记录表")
public class UpdateRecorde implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("更新版本")
    @TableField("version")
    private String version;

    @ApiModelProperty("更新记录备注")
    @TableField("description")
    private String description;

    @ApiModelProperty("项目更新时间")
    @TableField("recorde_time")
    private LocalDateTime recordeTime;

    @ApiModelProperty("添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;


}

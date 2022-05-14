package com.blog.entity;

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
 * 数据变更记录
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Getter
@Setter
@TableName("biz_data_change_log")
@ApiModel(value = "DataChangeLog对象", description = "数据变更记录")
public class DataChangeLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("数据变更记录类型. 0-基本数据变更, 1-状态数据变更")
    @TableField("change_type")
    private Long changeType;

    @ApiModelProperty("业务模块编号")
    @TableField("module_code")
    private String moduleCode;

    @ApiModelProperty("业务数据主键id")
    @TableField("business_id")
    private Long businessId;

    @ApiModelProperty("记录创建人id")
    @TableField("create_id")
    private Long createId;

    @ApiModelProperty("记录创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("数据变动内容")
    @TableField("data_diff")
    private String dataDiff;

    @ApiModelProperty("操作理由")
    @TableField("reason")
    private String reason;

    @ApiModelProperty("操作备注")
    @TableField("remark")
    private String remark;


}

package com.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 点赞表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Data
@TableName("biz_like")
@ApiModel(value = "Like对象", description = "点赞表")
public class Like implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("业务模块编号: 0-博客，1-评论，2-留言，3-图片")
    @TableField("module_code")
    private Integer moduleCode;

    @ApiModelProperty("业务数据主键id")
    @TableField("business_id")
    private Long businessId;

    @ApiModelProperty("操作用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty("操作类型: 0-点赞，1-点踩")
    @TableField("operation_type")
    private Boolean operationType;

    @ApiModelProperty("操作备注")
    @TableField("remark")
    private String remark;


}

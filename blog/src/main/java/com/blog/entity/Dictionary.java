package com.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Getter
@Setter
@TableName("biz_dictionary")
@ApiModel(value = "Dictionary对象", description = "字典表")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("组编码")
    @TableId("group_code")
    private String groupCode;

    @ApiModelProperty("组名字")
    @TableField("group_name")
    private String groupName;

    @ApiModelProperty("编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("排序")
    @TableField("order")
    private Integer order;

    @ApiModelProperty("语言")
    @TableField("language")
    private String language;

    @ApiModelProperty("0无效，1有效")
    @TableField("status")
    @TableLogic
    private Boolean status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}

package com.blog.entity.sys;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@TableName("biz_dictionary")
@ApiModel(value = "DictionaryEntity对象", description = "字典")
public class DictionaryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("组编码")
    @TableField("group_code")
    private String groupCode;

    @ApiModelProperty("组名字")
    @TableField("group_name")
    private String groupName;

    @ApiModelProperty("编码")
    @TableId("code")
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
    private Boolean status;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}

package com.blog.entity.sys;

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
 * 用户操作日志
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@TableName("biz_user_action_log")
@ApiModel(value = "UserActionLogEntity对象", description = "用户操作日志")
public class UserActionLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;

    @ApiModelProperty("请求链接")
    @TableField("url")
    private String url;

    @ApiModelProperty("操作名")
    @TableField("action_name")
    private String actionName;

    @ApiModelProperty("提交内容（json）")
    @TableField("params")
    private String params;

    @ApiModelProperty("操作人")
    @TableField("user_account_id")
    private Long userAccountId;

    @ApiModelProperty("操作时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}

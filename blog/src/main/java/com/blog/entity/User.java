package com.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Data
@TableName("biz_user")
@ApiModel(value = "User对象", description = "用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("手机号")
    @TableField("mobile")
    private String mobile;

    @ApiModelProperty("邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty("头像图片")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("生日")
    @TableField("birthday")
    private LocalDateTime birthday;

    @ApiModelProperty("性别: 0-男，1-女")
    @TableField("gender")
    private Boolean gender;

    @ApiModelProperty("密文密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("注册时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty("注册IP")
    @TableField("create_ip")
    private String createIp;

    @ApiModelProperty("最近登录IP")
    @TableField("last_login_ip")
    private String lastLoginIp;

    @ApiModelProperty("最近登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("登录次数")
    @TableField("login_count")
    private Long loginCount;

    @ApiModelProperty("更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty("用户状态: 0无效，1有效")
    @TableField("status")
    @TableLogic
    private Boolean status;

    @ApiModelProperty("删除标记")
    @TableField("delete_flag")
    private Boolean deleteFlag;

    @ApiModelProperty("语言编号")
    @TableField("language_code")
    private String languageCode;

    @ApiModelProperty("用户备注")
    @TableField("remark")
    private String remark;


}

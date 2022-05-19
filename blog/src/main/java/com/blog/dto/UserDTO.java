package com.blog.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Data
@ApiModel(value = "UserDTO对象", description = "用户注册DTO")
public class UserDTO {

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty("密文密码")
    @TableField("password")
    private String password;

}

package com.blog.controller.user;

import com.blog.entity.UserEntity;
import com.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/userEntity")
@Api(tags = "用户信息")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 通过id获取用户信息 信息
     *
     * @return 用户信息 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户信息信息", notes = "通过id获取用户信息信息", httpMethod = "GET")
    public UserEntity selectById(Long id) {
        return userService.selectById(id);
    }

}

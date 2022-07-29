package com.blog.controller.user;

import com.blog.dto.UserDTO;
import com.blog.entity.user.UserEntity;
import com.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户信息")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 用户注册
     *
     * @param request HttpServletRequest
     * @param user    用户实体类
     */
    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public void register(HttpServletRequest request, UserDTO user) throws UnknownHostException {
        userService.register(request, user);
    }

    /**
     * 通过id获取用户信息 信息
     *
     * @return 用户信息 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户信息信息")
    public UserEntity selectById(Long id) {
        return userService.selectById(id);
    }

}

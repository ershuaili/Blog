package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import com.blog.utils.IpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户", tags = "用户")
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return 用户信息列表
     */
    @GetMapping("/queryAll")
    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", httpMethod = "GET")
    public List<User> queryAll() {
        return userService.queryAll();
    }


    @RequestMapping("/urlName")
    public String methodName(HttpServletRequest request){
        String ip = IpUtil.getClientIpAddr(request);
        return IpUtil.getAddress(ip);
    }

}

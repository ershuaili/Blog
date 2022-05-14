package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 获取所有用户信息
     * @return 用户信息列表
     */
    @GetMapping("/queryAll")
    public List<User> queryAll() {
        return userService.queryAll();
    }

}

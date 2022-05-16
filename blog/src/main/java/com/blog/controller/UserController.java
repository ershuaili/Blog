package com.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/userEntity")
@Api(value = "用户信息表", tags = "用户信息表")
public class UserController {

}

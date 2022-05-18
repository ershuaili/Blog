package com.blog.controller.user;

import com.blog.entity.user.UserRoleEntity;
import com.blog.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/userRole")
@Api(tags = "用户角色")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    /**
     * 通过id获取用户角色 信息
     *
     * @return 用户角色 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户角色信息", notes = "通过id获取用户角色信息", httpMethod = "GET")
    public UserRoleEntity selectById(Long id) {
        return userRoleService.selectById(id);
    }

}

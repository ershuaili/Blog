package com.blog.controller.sys;

import com.blog.entity.sys.RoleEntity;
import com.blog.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/roleEntity")
@Api(tags = "角色")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 通过id获取角色 信息
     *
     * @return 角色 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取角色信息", notes = "通过id获取角色信息", httpMethod = "GET")
    public RoleEntity selectById(Long id) {
        return roleService.selectById(id);
    }

}

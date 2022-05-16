package com.blog.controller.sys;

import com.blog.entity.UserActionLogEntity;
import com.blog.service.UserActionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户操作日志 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/userActionLogEntity")
@Api(tags = "用户操作日志")
public class UserActionLogController {
    @Resource
    private UserActionLogService userActionLogService;

    /**
     * 通过id获取用户操作日志 信息
     *
     * @return 用户操作日志 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户操作日志信息", notes = "通过id获取用户操作日志信息", httpMethod = "GET")
    public UserActionLogEntity selectById(Long id) {
        return userActionLogService.selectById(id);
    }

}

package com.blog.controller.sys;

import com.blog.entity.DataChangeLogEntity;
import com.blog.service.DataChangeLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 数据变更记录 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/dataChangeLogEntity")
@Api(tags = "数据变更记录")
public class DataChangeLogController {
    @Resource
    private DataChangeLogService dataChangeLogService;

    /**
     * 通过id获取数据变更记录 信息
     *
     * @return 数据变更记录 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取数据变更记录信息", notes = "通过id获取数据变更记录信息", httpMethod = "GET")
    public DataChangeLogEntity selectById(Long id) {
        return dataChangeLogService.selectById(id);
    }

}

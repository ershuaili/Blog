package com.blog.controller.sys;

import com.blog.entity.UpdateRecordeEntity;
import com.blog.service.UpdateRecordeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 项目更新记录 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/updateRecordeEntity")
@Api(tags = "项目更新记录")
public class UpdateRecordeController {
    @Resource
    private UpdateRecordeService updateRecordeService;

    /**
     * 通过id获取项目更新记录 信息
     *
     * @return 项目更新记录 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取项目更新记录信息", notes = "通过id获取项目更新记录信息", httpMethod = "GET")
    public UpdateRecordeEntity selectById(Long id) {
        return updateRecordeService.selectById(id);
    }

}

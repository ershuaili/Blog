package com.blog.controller.sys;

import com.blog.entity.sys.DictionaryEntity;
import com.blog.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 字典 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/dictionaryEntity")
@Api(tags = "字典")
public class DictionaryController {
    @Resource
    private DictionaryService dictionaryService;

    /**
     * 通过id获取字典 信息
     *
     * @return 字典 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取字典信息", notes = "通过id获取字典信息", httpMethod = "GET")
    public DictionaryEntity selectById(Long id) {
        return dictionaryService.selectById(id);
    }

}

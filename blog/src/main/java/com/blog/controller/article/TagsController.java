package com.blog.controller.article;

import com.blog.entity.TagsEntity;
import com.blog.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/tagsEntity")
@Api(tags = "标签")
public class TagsController {
    @Resource
    private TagsService tagsService;

    /**
     * 通过id获取标签 信息
     *
     * @return 标签 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取标签信息", notes = "通过id获取标签信息", httpMethod = "GET")
    public TagsEntity selectById(Long id) {
        return tagsService.selectById(id);
    }

}

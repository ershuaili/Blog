package com.blog.controller.article;

import com.blog.entity.article.TypeEntity;
import com.blog.service.TypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 文章类型 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/type")
@Api(tags = "文章类型")
public class TypeController {
    @Resource
    private TypeService typeService;

    /**
     * 通过id获取文章类型 信息
     *
     * @return 文章类型 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取文章类型信息", notes = "通过id获取文章类型信息", httpMethod = "GET")
    public TypeEntity selectById(Long id) {
        return typeService.selectById(id);
    }

}

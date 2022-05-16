package com.blog.controller.article;

import com.blog.entity.ArticleLookEntity;
import com.blog.service.ArticleLookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户浏览记录 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/articleLookEntity")
@Api(tags = "用户浏览记录")
public class ArticleLookController {
    @Resource
    private ArticleLookService articleLookService;

    /**
     * 通过id获取用户浏览记录 信息
     *
     * @return 用户浏览记录 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户浏览记录信息", notes = "通过id获取用户浏览记录信息", httpMethod = "GET")
    public ArticleLookEntity selectById(Long id) {
        return articleLookService.selectById(id);
    }

}

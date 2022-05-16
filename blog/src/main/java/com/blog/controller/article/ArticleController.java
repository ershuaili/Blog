package com.blog.controller.article;

import com.blog.entity.ArticleEntity;
import com.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 文章 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/articleEntity")
@Api(tags = "文章")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 通过id获取文章 信息
     *
     * @return 文章 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取文章信息", notes = "通过id获取文章信息", httpMethod = "GET")
    public ArticleEntity selectById(Long id) {
        return articleService.selectById(id);
    }

}

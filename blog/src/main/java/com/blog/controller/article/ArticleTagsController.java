package com.blog.controller.article;

import com.blog.entity.article.ArticleTagsEntity;
import com.blog.service.ArticleTagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 文章标签 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/articleTags")
@Api(tags = "文章标签")
public class ArticleTagsController {
    @Resource
    private ArticleTagsService articleTagsService;

    /**
     * 通过id获取文章标签 信息
     *
     * @return 文章标签 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取文章标签信息", notes = "通过id获取文章标签信息", httpMethod = "GET")
    public ArticleTagsEntity selectById(Long id) {
        return articleTagsService.selectById(id);
    }

}

package com.blog.controller.article;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.entity.article.ArticleEntity;
import com.blog.exception.SuccessResult;
import com.blog.service.ArticleService;
import com.blog.utils.PageInfo;
import com.blog.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/article")
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

    /**
     * 分页查询文章信息
     *
     * @param param 文章信息实体类
     * @return 文章信息列表
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页查询文章信息", notes = "分页查询文章信息", httpMethod = "POST")
    public PageInfo<ArticleVO> page(@RequestBody ArticleEntity param) {
        return articleService.page(param);
    }

}

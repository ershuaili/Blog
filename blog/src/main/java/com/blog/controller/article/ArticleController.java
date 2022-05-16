package com.blog.controller.article;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/articleEntity")
@Api(value = "文章表", tags = "文章表")
public class ArticleController {

}
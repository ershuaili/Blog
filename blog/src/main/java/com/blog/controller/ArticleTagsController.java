package com.blog.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章标签表 前端控制器
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/articleTagsEntity")
@Api(value = "文章标签表", tags = "文章标签表")
public class ArticleTagsController {

}

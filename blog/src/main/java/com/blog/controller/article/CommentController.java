package com.blog.controller.article;

import com.blog.entity.article.CommentEntity;
import com.blog.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户评论 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/comment")
@Api(tags = "用户评论")
public class CommentController {
    @Resource
    private CommentService commentService;

    /**
     * 通过id获取用户评论 信息
     *
     * @return 用户评论 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取用户评论信息", notes = "通过id获取用户评论信息", httpMethod = "GET")
    public CommentEntity selectById(Long id) {
        return commentService.selectById(id);
    }

}

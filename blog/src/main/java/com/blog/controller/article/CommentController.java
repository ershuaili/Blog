package com.blog.controller.article;

import com.blog.dto.CommentDTO;
import com.blog.entity.article.CommentEntity;
import com.blog.exception.Result;
import com.blog.service.CommentService;
import com.blog.utils.PageInfo;
import com.blog.vo.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "添加评论")
    @PostMapping("/add")
    public Result<?> saveComment(@RequestBody CommentDTO commentdto){
        commentService.saveComment(commentdto);
        return Result.ok();
    }

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

    /**
     * 分页获取评论信息
     *
     * @param param 评论实体类
     * @return 评论信息分页
     */
    @PostMapping("/page")
    @ApiOperation(value = "分页获取评论信息", notes = "分页获取评论信息", httpMethod = "POST")
    public PageInfo<CommentVO> page(@RequestBody CommentDTO param) {
        return commentService.page(param);
    }
}

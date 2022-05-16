package com.blog.controller.article;

import com.blog.entity.LikeEntity;
import com.blog.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 点赞 前端控制器
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@RestController
@RequestMapping("/likeEntity")
@Api(tags = "点赞")
public class LikeController {
    @Resource
    private LikeService likeService;

    /**
     * 通过id获取点赞 信息
     *
     * @return 点赞 信息
     */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取点赞信息", notes = "通过id获取点赞信息", httpMethod = "GET")
    public LikeEntity selectById(Long id) {
        return likeService.selectById(id);
    }

}

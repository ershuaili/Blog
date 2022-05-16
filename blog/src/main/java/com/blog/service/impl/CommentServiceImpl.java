package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.CommentEntity;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户评论表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {
    @Resource
    private CommentMapper commentMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<CommentEntity> getConditionsByEntity(CommentEntity param) {
        QueryWrapper<CommentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                //
                .eq(param.getId() != null, CommentEntity::getId, param.getId())
                // 被评论文章ID
                .eq(param.getArticleId() != null, CommentEntity::getArticleId, param.getArticleId())
                // 评论人ID
                .eq(param.getUserId() != null, CommentEntity::getUserId, param.getUserId())
                // 父级评论id
                .eq(param.getPid() != null, CommentEntity::getPid, param.getPid())
                // 评论时ip
                .eq(!StringUtils.isEmpty(param.getIp()), CommentEntity::getIp, param.getIp())
                // 评论时系统类型
                .eq(!StringUtils.isEmpty(param.getOs()), CommentEntity::getOs, param.getOs())
                // 评论时浏览器类型
                .eq(!StringUtils.isEmpty(param.getBrowser()), CommentEntity::getBrowser, param.getBrowser())
                // 评论内容
                .eq(!StringUtils.isEmpty(param.getContent()), CommentEntity::getContent, param.getContent())
                // 支持（赞）
                .eq(param.getSupport() != null, CommentEntity::getSupport, param.getSupport())
                // 反对（踩）
                .eq(param.getOppose() != null, CommentEntity::getOppose, param.getOppose())
                // 添加时间
                .eq(param.getCreateTime() != null, CommentEntity::getCreateTime, param.getCreateTime())
                // 更新时间
                .eq(param.getUpdateTime() != null, CommentEntity::getUpdateTime, param.getUpdateTime())
        ;
        return queryWrapper;
    }

}

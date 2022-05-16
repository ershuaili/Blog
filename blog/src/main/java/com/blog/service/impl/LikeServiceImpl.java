package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.LikeEntity;
import com.blog.mapper.LikeMapper;
import com.blog.service.LikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class LikeServiceImpl extends ServiceImpl<LikeMapper, LikeEntity> implements LikeService {
    @Resource
    private LikeMapper likeMapper;


    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<LikeEntity> getConditionsByEntity(LikeEntity param) {
        QueryWrapper<LikeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, LikeEntity::getId, param.getId())
                // 业务模块编号: 0-博客，1-评论，2-留言，3-图片
                .eq(param.getModuleCode() != null, LikeEntity::getModuleCode, param.getModuleCode())
                // 业务数据主键id
                .eq(param.getBusinessId() != null, LikeEntity::getBusinessId, param.getBusinessId())
                // 操作用户id
                .eq(param.getUserId() != null, LikeEntity::getUserId, param.getUserId())
                // 操作类型: 0-点赞，1-点踩
                .eq(param.getOperationType() != null, LikeEntity::getOperationType, param.getOperationType())
                // 操作备注
                .eq(!StringUtils.isEmpty(param.getRemark()), LikeEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

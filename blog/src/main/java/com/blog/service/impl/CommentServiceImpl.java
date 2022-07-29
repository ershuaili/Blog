package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.dto.CommentDTO;
import com.blog.entity.article.CommentEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.CommentMapper;
import com.blog.service.CommentService;
import com.blog.utils.ConvertUtils;
import com.blog.utils.PageInfo;
import com.blog.vo.CommentVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户评论 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    /**
     * 用户评论新增
     *
     * @param commentDTO 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveComment(CommentDTO commentDTO) {

        CommentEntity comment = CommentEntity.builder()
                .articleId(commentDTO.getArticleId())
                .userId(commentDTO.getUserId())
                .content(commentDTO.getContent())
                .pid(commentDTO.getPid())
                .createTime(LocalDateTime.now())
                .build();
        commentMapper.insert(comment);
    }


    /**
     * 用户评论修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(CommentEntity param) {
        if (commentMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户评论删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (commentMapper.deleteById(id) == 0) {
            throw new BusinessException(BusinessErrorCodes.DELETE_FAILED);
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    @Override
    public CommentEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return commentMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<CommentEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<CommentEntity> entities = commentMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<CommentEntity> listByConditions(CommentEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<CommentEntity> queryWrapper = getConditionsByEntity(conditions);

        List<CommentEntity> entities = commentMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 分页获取评论信息
     *
     * @param param 查询参数
     * @return 评论信息分页
     */
    @Override
    public PageInfo<CommentVO> page(CommentDTO param) {
        //DTO转实体类
        CommentEntity comment = ConvertUtils.convert(param, CommentEntity.class);
        // 根据文章id分页查询所有父评论
        QueryWrapper<CommentEntity> queryWrapper = this.getConditionsByEntity(comment);
        queryWrapper.lambda().isNull(CommentEntity::getPid);
        Page<CommentEntity> page = new Page<>(param.getPageNum(), param.getPageSize());
        Page<CommentEntity> commentEntityPage = commentMapper.selectPage(page, queryWrapper);
        // 根据父评论id查询所有子评论id
        List<CommentEntity> commentEntityList = commentEntityPage.getRecords();
        List<Long> parentIds = commentEntityList.stream().map(CommentEntity::getId).collect(Collectors.toList());
        QueryWrapper<CommentEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(parentIds.size() != 0, CommentEntity::getId, parentIds);
        List<CommentEntity> childEntityList = commentMapper.selectList(wrapper);
        Map<Long, List<CommentEntity>> childMap =
                childEntityList.stream().collect(Collectors.groupingBy(CommentEntity::getPid));

        List<CommentVO> commentVOList = ConvertUtils.convert(commentEntityList, CommentVO.class);
        for (CommentVO vo : commentVOList) {
            vo.setChildComment(childMap.get(vo.getId()));
        }

        return PageInfo.<CommentVO>builder()
                .pageSize(param.getPageSize())
                .pageNum(param.getPageNum())
                .totalNum(commentEntityPage.getTotal())
                .records(commentVOList)
                .build();
    }

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

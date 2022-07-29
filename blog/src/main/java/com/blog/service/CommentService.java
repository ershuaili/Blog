package com.blog.service;

import com.blog.dto.CommentDTO;
import com.blog.entity.article.CommentEntity;
import com.blog.utils.PageInfo;
import com.blog.vo.CommentVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户评论 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface CommentService {

    /**
     * 用户评论新增
     *
     * @param commentVO 评论对象
     */
    void saveComment(CommentDTO commentDTO);

    /**
     * 用户评论修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(CommentEntity param);

    /**
     * 用户评论删除(单个条目)
     *
     * @param id-id
     */
    void removeById(Long id);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    CommentEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<CommentEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<CommentEntity> listByConditions(CommentEntity conditions);

    /**
     * 分页获取评论信息
     * @param param 查询参数
     * @return 评论信息分页
     */
    PageInfo<CommentVO> page(CommentDTO param);
}

package com.blog.service;

import com.blog.entity.article.ArticleEntity;
import com.blog.utils.PageInfo;
import com.blog.vo.ArticleVO;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
public interface ArticleService {

    /**
     * 文章新增
     *
     * @param param 根据需要进行传值
     */
    void add(ArticleEntity param);

    /**
     * 文章修改
     *
     * @param param 根据需要进行传值
     */
    void updateById(ArticleEntity param);

    /**
     * 文章删除(单个条目)
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
    ArticleEntity selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<ArticleEntity> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<ArticleEntity> listByConditions(ArticleEntity conditions);

    /**
     * 分页查询文章信息列表
     *
     * @param param 查询条件
     * @return 文章分页
     */
    PageInfo<ArticleVO> page(ArticleEntity param);
}

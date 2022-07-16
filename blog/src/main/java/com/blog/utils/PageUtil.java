package com.blog.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Builder;

import java.io.Serializable;

/**
 * <p>
 * 分页信息
 * </p>
 *
 * @author 李二帅
 * @since 2022-07-16
 */
@Builder
public class PageUtil<T> implements Serializable {

    public static <T> PageInfo<T> getPage(IPage<T> page) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotalNum(page.getTotal());
        pageInfo.setRecords(page.getRecords());
        return pageInfo;
    }

}

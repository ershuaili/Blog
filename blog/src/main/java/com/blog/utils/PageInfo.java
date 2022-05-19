package com.blog.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 分页信息
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 记录列表
     */
    private List<T> records;
    /**
     * 页码
     */
    private Long pageNum;
    /**
     * 每页显示长度
     */
    private Long pageSize;
    /**
     * 总的记录条数
     */
    private Long totalNum;

    public static <T> PageInfo<T> getPage(IPage<T> page) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setTotalNum((long) page.getRecords().size());
        pageInfo.setRecords(page.getRecords());
        return pageInfo;
    }

}

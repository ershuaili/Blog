package com.blog.web;

import com.blog.utils.ConvertUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分页信息
 * </p>
 *
 * @author 李二帅
 * @since 2022-07-16
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
    private Long pageNum = 1L;
    /**
     * 每页显示长度
     */
    private Long pageSize = 10L;
    /**
     * 总的记录条数
     */
    private Long totalNum;

    public static <T> PageInfo<T> entityPage(Long pageNum, Long pageSize) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setTotalNum(0L);
        pageInfo.setRecords(new ArrayList<>(0));

        return pageInfo;
    }


    /**
     * 转换pageInfo内部数据对象
     *
     * @param pageInfo 要转换的pageInfo对象
     * @param cla      需要转换为的类型
     * @param <T>      需要转换为的类型泛型
     * @return 转换完毕后的pageInfo对象
     */
    public static <T> PageInfo<T> convertPageInfo(PageInfo<?> pageInfo, Class<T> cla) {
        if (null == pageInfo) {
            return null;
        }

        List<T> newRecords;
        if (null == pageInfo.getRecords() || 0 == pageInfo.getRecords().size()) {
            newRecords = new ArrayList<>(0);
        } else {
            newRecords = ConvertUtils.convert(pageInfo.getRecords(), cla);
        }

        return PageInfo.<T>builder()
                .pageSize(pageInfo.getPageSize())
                .pageNum(pageInfo.getPageNum())
                .totalNum(pageInfo.getTotalNum())
                .records(newRecords)
                .build();
    }
}

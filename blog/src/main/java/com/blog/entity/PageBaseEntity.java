package com.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 分页实体类
 */
@Data
public class PageBaseEntity {
    /**
     * 总的记录条数
     */
    @TableField(exist = false)
    private Long totalNum;

    /**
     * 页码
     */
    @TableField(exist = false)
    private Long pageNum = 1L;

    /**
     * 每页显示长度
     */
    @TableField(exist = false)
    private Long pageSize = 10L;
}

package com.blog.service.impl;

import com.blog.entity.DataChangeLog;
import com.blog.mapper.DataChangeLogMapper;
import com.blog.service.DataChangeLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据变更记录 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Service
public class DataChangeLogServiceImpl extends ServiceImpl<DataChangeLogMapper, DataChangeLog> implements DataChangeLogService {

}

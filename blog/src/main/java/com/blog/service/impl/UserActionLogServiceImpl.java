package com.blog.service.impl;

import com.blog.entity.UserActionLog;
import com.blog.mapper.UserActionLogMapper;
import com.blog.service.UserActionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户操作日志 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Service
public class UserActionLogServiceImpl extends ServiceImpl<UserActionLogMapper, UserActionLog> implements UserActionLogService {

}

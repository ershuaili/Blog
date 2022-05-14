package com.blog.mapper;

import com.blog.entity.UserActionLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户操作日志 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
@Mapper
public interface UserActionLogMapper extends BaseMapper<UserActionLog> {

}

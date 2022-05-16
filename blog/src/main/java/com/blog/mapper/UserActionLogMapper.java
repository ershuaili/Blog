package com.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.entity.UserActionLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户操作日志 Mapper 接口
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Mapper
public interface UserActionLogMapper extends BaseMapper<UserActionLogEntity> {

}

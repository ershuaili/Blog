package com.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.entity.User;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author 作者
 * @since 2022-05-14
 */
public interface UserService extends IService<User> {

    /**
     * 获取所有用户信息
     * @return 用户信息列表
     */
    List<User> queryAll();
}

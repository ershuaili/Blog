package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.dto.UserDTO;
import com.blog.entity.user.UserEntity;
import com.blog.entity.user.UserRoleEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.UserMapper;
import com.blog.mapper.UserRoleMapper;
import com.blog.service.UserService;
import com.blog.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 用户信息新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserEntity param) {
        if (userMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 用户信息修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(UserEntity param) {
        if (userMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 用户信息删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (userMapper.deleteById(id) == 0) {
            throw new BusinessException(BusinessErrorCodes.DELETE_FAILED);
        }
    }

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    @Override
    public UserEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return userMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<UserEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<UserEntity> entities = userMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<UserEntity> listByConditions(UserEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<UserEntity> queryWrapper = getConditionsByEntity(conditions);

        List<UserEntity> entities = userMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public UserEntity queryUserByNickname(String userName) {
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(!StringUtils.isEmpty(userName), UserEntity::getName, userName);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 用户注册
     *
     * @param user 用户实体类
     */
    @Override
    public void register(HttpServletRequest request, UserDTO user) {
        UserEntity userEntity = new UserEntity();
        // 用户邮箱已存在
        userEntity.setMail(user.getMail());
        List<UserEntity> userEntities = this.listByConditions(userEntity);
        if (0 != userEntities.size()) {
            throw new BusinessException(BusinessErrorCodes.USER_MAIL_HAS_EXISTED);
        }
        // 用户名
        userEntity.setName(user.getMail());
        //用户昵称
        userEntity.setNickname(user.getNickname());
        // 用户密码
        userEntity.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        //用户手机
        userEntity.setMobile(user.getMobile());
        // 用户头像默认地址
        userEntity.setAvatar("http://47.96.145.7:8090/picture/user.jpg");
        // 注册时间
        userEntity.setCreateTime(LocalDateTime.now());
        // 注册IP
        userEntity.setCreateIp(IpUtil.getClientIpAddress(request));
        userMapper.insert(userEntity);

        UserRoleEntity userRoleEntity = new UserRoleEntity();

        // 用户id
        userRoleEntity.setUserId(userEntity.getId());
        // 角色id
        userRoleEntity.setRoleId(2L);
        //创建时间
        userRoleEntity.setCreateTime(LocalDateTime.now());

        userRoleMapper.insert(userRoleEntity);

    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<UserEntity> getConditionsByEntity(UserEntity param) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 主键
                .eq(param.getId() != null, UserEntity::getId, param.getId())
                // 用户名
                .eq(!StringUtils.isEmpty(param.getName()), UserEntity::getName, param.getName())
                // 用户昵称
                .eq(!StringUtils.isEmpty(param.getNickname()), UserEntity::getNickname, param.getNickname())
                // 手机号
                .eq(!StringUtils.isEmpty(param.getMobile()), UserEntity::getMobile, param.getMobile())
                // 邮箱
                .eq(!StringUtils.isEmpty(param.getMail()), UserEntity::getMail, param.getMail())
                // 头像图片
                .eq(!StringUtils.isEmpty(param.getAvatar()), UserEntity::getAvatar, param.getAvatar())
                // 生日
                .eq(param.getBirthday() != null, UserEntity::getBirthday, param.getBirthday())
                // 性别: 0-男，1-女
                .eq(param.getGender() != null, UserEntity::getGender, param.getGender())
                // 密文密码
                .eq(!StringUtils.isEmpty(param.getPassword()), UserEntity::getPassword, param.getPassword())
                // 注册时间
                .eq(param.getCreateTime() != null, UserEntity::getCreateTime, param.getCreateTime())
                // 注册IP
                .eq(!StringUtils.isEmpty(param.getCreateIp()), UserEntity::getCreateIp, param.getCreateIp())
                // 最近登录IP
                .eq(!StringUtils.isEmpty(param.getLastLoginIp()), UserEntity::getLastLoginIp, param.getLastLoginIp())
                // 最近登录时间
                .eq(param.getLastLoginTime() != null, UserEntity::getLastLoginTime, param.getLastLoginTime())
                // 登录次数
                .eq(param.getLoginCount() != null, UserEntity::getLoginCount, param.getLoginCount())
                // 更新时间
                .eq(param.getUpdateTime() != null, UserEntity::getUpdateTime, param.getUpdateTime())
                // 用户状态: 0无效，1有效
                .eq(param.getStatus() != null, UserEntity::getStatus, param.getStatus())
                // 删除标记
                .eq(param.getDeleteFlag() != null, UserEntity::getDeleteFlag, param.getDeleteFlag())
                // 语言编号
                .eq(!StringUtils.isEmpty(param.getLanguageCode()), UserEntity::getLanguageCode, param.getLanguageCode())
                // 用户备注
                .eq(!StringUtils.isEmpty(param.getRemark()), UserEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

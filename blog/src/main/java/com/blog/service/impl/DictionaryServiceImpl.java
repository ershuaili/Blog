package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.entity.DictionaryEntity;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;
import com.blog.mapper.DictionaryMapper;
import com.blog.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 字典 服务实现类
 * </p>
 *
 * @author 李二帅
 * @since 2022-05-16
 */
@Slf4j
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryMapper dictionaryMapper;

    /**
     * 字典新增
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(DictionaryEntity param) {
        if (dictionaryMapper.insert(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
     * 字典修改
     *
     * @param param 根据需要进行传值
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(DictionaryEntity param) {
        if (dictionaryMapper.updateById(param) == 0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
     * 字典删除(单个条目)
     *
     * @param id-id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if (dictionaryMapper.deleteById(id) == 0) {
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
    public DictionaryEntity selectById(Long id) {
        if (null == id) {
            return null;
        }
        return dictionaryMapper.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<DictionaryEntity> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<DictionaryEntity> entities = dictionaryMapper.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<DictionaryEntity> listByConditions(DictionaryEntity conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<DictionaryEntity> queryWrapper = getConditionsByEntity(conditions);

        List<DictionaryEntity> entities = dictionaryMapper.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<DictionaryEntity> getConditionsByEntity(DictionaryEntity param) {
        QueryWrapper<DictionaryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                // 组编码
                .eq(!StringUtils.isEmpty(param.getGroupCode()), DictionaryEntity::getGroupCode, param.getGroupCode())
                // 组名字
                .eq(!StringUtils.isEmpty(param.getGroupName()), DictionaryEntity::getGroupName, param.getGroupName())
                // 编码
                .eq(!StringUtils.isEmpty(param.getCode()), DictionaryEntity::getCode, param.getCode())
                // 名称
                .eq(!StringUtils.isEmpty(param.getName()), DictionaryEntity::getName, param.getName())
                // 排序
                .eq(param.getOrder() != null, DictionaryEntity::getOrder, param.getOrder())
                // 语言
                .eq(!StringUtils.isEmpty(param.getLanguage()), DictionaryEntity::getLanguage, param.getLanguage())
                // 0无效，1有效
                .eq(param.getStatus() != null, DictionaryEntity::getStatus, param.getStatus())
                // 备注
                .eq(!StringUtils.isEmpty(param.getRemark()), DictionaryEntity::getRemark, param.getRemark())
        ;
        return queryWrapper;
    }

}

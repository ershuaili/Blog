package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.entity.DictionaryEntity;
import com.blog.mapper.DictionaryMapper;
import com.blog.service.DictionaryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author
 * @since 2022-05-16
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, DictionaryEntity> implements DictionaryService {
    @Resource
    private DictionaryMapper dictionaryMapper;


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

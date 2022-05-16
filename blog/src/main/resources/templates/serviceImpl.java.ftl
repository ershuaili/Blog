package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.blog.enums.BusinessErrorCodes;
import com.blog.exception.BusinessException;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Slf4j
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} implements ${table.serviceName} {

    @Resource
    private ${table.mapperName} ${table.mapperName?uncap_first};

    /**
    * ${table.comment!}新增
    * @param param 根据需要进行传值
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(${entity} param) {
        if (${table.mapperName?uncap_first}.insert(param)==0) {
            throw new BusinessException(BusinessErrorCodes.INSERT_FAILED);
        }
    }

    /**
    * ${table.comment!}修改
    * @param param 根据需要进行传值
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(${entity} param) {
        if (${table.mapperName?uncap_first}.updateById(param)==0) {
            throw new BusinessException(BusinessErrorCodes.UPDATE_FAILED);
        }
    }

    /**
    * ${table.comment!}删除(单个条目)
    * @param id-id
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeById(Long id) {
        if(${table.mapperName?uncap_first}.deleteById(id)==0){
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
    public ${entity} selectById(Long id) {
        if (null == id) {
            return null;
        }
        return ${table.mapperName?uncap_first}.selectById(id);
    }

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    @Override
    public List<${entity}> listByIds(Collection<Long> ids) {
        if (null == ids || 0 == ids.size()) {
            return new ArrayList<>(0);
        }

        List<${entity}> entities = ${table.mapperName?uncap_first}.selectBatchIds(ids);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    @Override
    public List<${entity}> listByConditions(${entity} conditions) {
        if (null == conditions) {
            return new ArrayList<>(0);
        }
        QueryWrapper<${entity}> queryWrapper = getConditionsByEntity(conditions);

        List<${entity}> entities = ${table.mapperName?uncap_first}.selectList(queryWrapper);
        return null == entities ? new ArrayList<>(0) : entities;
    }

    /**
     * 构建查询 query wrapper
     *
     * @param param 查询条件entity
     * @return 对应的query wrapper
     */
    private QueryWrapper<${entity}> getConditionsByEntity(${entity} param) {
        QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
        <#list table.fields as field>
            // ${field.comment}
            <#if !entityLombokModel>
                <#if field.propertyType == "Boolean">
                    <#assign getprefix="is"/>
                <#else>
                    <#assign getprefix="get"/>
                </#if>
                <#if field.propertyType == "String">
                    .eq(!StringUtils.isEmpty(param.${getprefix}${field.capitalName}()), ${entity}::${getprefix}${field.capitalName}, param.${getprefix}${field.capitalName}())
                <#else>
                    .eq(param.${getprefix}${field.capitalName}() != null, ${entity}::${getprefix}${field.capitalName}, param.${getprefix}${field.capitalName}())
                </#if>
            <#else>
                <#if field.propertyType == "String">
                    .eq(!StringUtils.isEmpty(param.get${field.capitalName}()), ${entity}::get${field.capitalName}, param.get${field.capitalName}())
                <#else>
                    .eq(param.get${field.capitalName}() != null, ${entity}::get${field.capitalName}, param.get${field.capitalName}())
                </#if>
            </#if>
        </#list>;
        return queryWrapper;
    }

}
</#if>

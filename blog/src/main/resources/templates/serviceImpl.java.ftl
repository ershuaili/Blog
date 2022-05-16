package ${package.ServiceImpl};

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {
 @Resource
 private ${table.mapperName} ${table.mapperName?uncap_first};


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

package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
import io.swagger.annotations.Api;
import javax.annotation.Resource;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
<#else>


</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(tags = "${table.comment!}")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>
    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 通过id获取${table.comment!} 信息
    * @return ${table.comment!} 信息
    */
    @GetMapping("/selectById")
    @ApiOperation(value = "通过id获取${table.comment!}信息", notes = "通过id获取${table.comment!}信息", httpMethod = "GET")
    public ${entity} selectById(Long id) {
    return ${table.serviceName?uncap_first}.selectById(id);
    }

    }
</#if>

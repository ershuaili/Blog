package ${package.Service};

import ${package.Entity}.${entity};

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {

    /**
    * ${table.comment!}新增
    * @param param 根据需要进行传值
    */
    void add(${entity} param);

    /**
    * ${table.comment!}修改
    * @param param 根据需要进行传值
    */
    void updateById(${entity} param);

    /**
    * ${table.comment!}删除(单个条目)
    * @param id-id
    */
    void removeById(Long id);

    /**
     * 根据id查询数据
     *
     * @param id id
     * @return 查询到的数据
     */
    ${entity} selectById(Long id);

    /**
     * 根据id查询数据
     *
     * @param ids id列表
     * @return 查询到的数据
     */
    List<${entity}> listByIds(Collection<Long> ids);

    /**
     * 根据条件查询数据
     *
     * @param conditions 查询条件
     * @return 查询到的数据
     */
    List<${entity}> listByConditions(${entity} conditions);

}
</#if>

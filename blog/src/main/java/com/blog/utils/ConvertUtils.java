package com.blog.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 类型转换工具
 */
public class ConvertUtils {

    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        try {
            T target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> convert(List source, Class<T> clazz) {
        ArrayList<T> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(source)) {
            return result;
        }
        for (Object s : source) {
            result.add(convert(s, clazz));
        }
        return result;
    }

    /**
     * entity转vo
     *
     * @param entityPage 分页实体
     * @param <V>        vo
     * @param <T>        entity
     * @return VO分页
     */
    public static <V, T> PageInfo<V> convertPage(Page<T> entityPage, Class<V> v) {
        if (null == entityPage) {
            return null;
        }
        PageInfo<T> page = PageUtil.getPage(entityPage);
        PageInfo<V> voPage = PageInfo.<V>builder()
                .pageNum(page.getPageNum())
                .pageSize(page.getPageSize())
                .totalNum(page.getTotalNum())
                .build();
        if (null != entityPage.getRecords()) {
            voPage.setRecords(ConvertUtils.convert(entityPage.getRecords(), v));
        }
        return voPage;
    }

    /**
     * Take each object in map.
     * The way that get the key of object is `keyFun`.
     *
     * @param objs   The objects.
     * @param keyFun The function that is used to get the key of object.
     *               Function will return a blank HashMap if the `keyFun` is null.
     * @param map    Three
     * @param filter The filter function.
     * @param <K>    Key type.
     * @param <O>    Object type.
     * @return Return the map that after handle.
     */
    public static <K, O, V> Map<K, V> groupSingleByKey(final Collection<O> objs,
                                                       final Function<? super O, ? extends K> keyFun,
                                                       final Function<? super O, ? extends V> map,
                                                       final Predicate<? super O> filter) {
        if (null == objs || 0 == objs.size() || null == keyFun) {
            return new HashMap<>(0);
        }

        Map<K, V> resultMap = new HashMap<>();
        for (O obj : objs) {
            if (null == obj) {
                continue;
            }
            if (null != filter && !filter.test(obj)) {
                continue;
            }

            resultMap.put(keyFun.apply(obj), map.apply(obj));
        }
        return resultMap;
    }

}
package com.blog.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换工具
 */
public class ConvertUtil {

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
}
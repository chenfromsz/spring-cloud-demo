package com.demo.data.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class CopyUtil {

    private static Logger logger = LoggerFactory.getLogger(CopyUtil.class);

    /**
     * 复制对象
     *
     * @param src   原对象
     * @param clazz 对象类
     * @param <T>
     * @return 新的对象
     */
    public static <T> T copy(Object src, Class<T> clazz) {
        if (src == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(src, t);
            return t;
        } catch (Exception e) {
            logger.error("复制文件出错", e);
        }
        return t;
    }

    /**
     * 复制对象list集合
     *
     * @param srclist 原对象集合
     * @param clazz   对象类
     * @param <T>
     * @return 新的对象
     */
    public static <T> List<T> copyList(Object srclist, Class<T> clazz) {
        if (srclist == null) {
            return null;
        }
        List<T> destlist = new ArrayList<T>();
        List<Object> srcLists = (List<Object>) srclist;
        for (Object src : srcLists) {
            destlist.add(copy(src, clazz));
        }
        return destlist;
    }

}

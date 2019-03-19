package com.demo.data.util;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * map转成bean转换工具类
 */
public class MapToBeanUtil {
    private static final Logger logger = LoggerFactory.getLogger(MapToBeanUtil.class);

    /**
     * 将List MAP转换成VO对象
     * @param list
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> transListMap2ListBean(List<Map<String,Object>> list , Class<T> clazz) throws Exception{
        List<T> listResult = new ArrayList<T>();
        for(Map<String,Object> map:list){
            listResult.add(transMap2Bean(map,clazz));
        }
        return listResult;
    }

    /**
     * 将List VO转换成List MAP对象
     * @throws Exception
     */
    public static List<Map<String,Object>> transListBean2ListMap(List<Object> list) throws Exception{
        List<Map<String,Object>> listResult = new ArrayList<Map<String,Object>>();
        for(Object o:list){
            listResult.add(transBean2Map(o));
        }
        return listResult;
    }

    /**
     * 将MAP转换成VO对象
     * @param map
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T transMap2Bean(Map<String,Object> map , Class<T> clazz) throws Exception{

        T vo = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Map.Entry<String,Object> entry : map.entrySet()){
            setValue(vo, clazz, fields, entry.getKey(), entry.getValue());
        }
        return vo;
    }

    /**
     * 将VO转换成MAP对象
     * @throws Exception
     */
    public static Map<String,Object> transBean2Map(Object o) throws Exception{
        Map<String,Object> map = new HashMap<String, Object>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor pd = new PropertyDescriptor(field.getName(), o.getClass());
            map.put(field.getName(),pd.getReadMethod().invoke(o,null));
        }
        return map;
    }

    private static <T> void setValue(T bean,Class<T> clazz, Field[] fields, String fieldName, Object vlaue) throws Exception {
        PropertyDescriptor pd;
        Method setMethod;
        try {
            if (vlaue == null){
                return;
            }
            for (Field field : fields) {
                if (fieldName.replace("_","").toUpperCase().equals(field.getName().toUpperCase())) {
                    pd = new PropertyDescriptor(field.getName(), clazz);
                    setMethod = pd.getWriteMethod();
                    if (field.getType() == String.class) {
                        if (vlaue instanceof byte[]) {
                            setMethod.invoke(bean, new String((byte[]) vlaue));
                        } else if (vlaue instanceof Date){
                            setMethod.invoke(bean, CommonUtils.formatDateTime((Date)vlaue));
                        }else {
                            setMethod.invoke(bean, ObjectUtils.toString(vlaue));
                        }
                    } else if (field.getType() == Boolean.class) {
                        if (vlaue instanceof Boolean) {
                            setMethod.invoke(bean, vlaue);
                        } else if (vlaue instanceof String) {
                            setMethod.invoke(bean, Boolean.parseBoolean((String) vlaue));
                        } else if (vlaue instanceof Number) {
                            Integer v = ((Number) vlaue).intValue();
                            if (v == 0) {
                                setMethod.invoke(bean, false);
                            } else {
                                setMethod.invoke(bean, true);
                            }
                        }
                    } else if (field.getType() == Date.class){
                        if (vlaue instanceof byte[]) {
                            setMethod.invoke(bean, CommonUtils.dateStrToDate(new String((byte[]) vlaue)));
                        } else if (vlaue instanceof Date){
                            setMethod.invoke(bean, ((Date)vlaue));
                        }else {
                            setMethod.invoke(bean, CommonUtils.dateStrToDate((String)vlaue));
                        }
                    }else {
                        if (vlaue instanceof Number) {
                            if (field.getType() == Integer.class) {
                                setMethod.invoke(bean, ((Number) vlaue).intValue());
                            } else if (field.getType() == Long.class) {
                                setMethod.invoke(bean, ((Number) vlaue).longValue());
                            } else if (field.getType() == Double.class) {
                                setMethod.invoke(bean, ((Number) vlaue).doubleValue());
                            } else if (field.getType() == Float.class) {
                                setMethod.invoke(bean, ((Number) vlaue).floatValue());
                            } else {
                                setMethod.invoke(bean, vlaue);
                            }
                        } else {
                            setMethod.invoke(bean, vlaue);
                        }
                    }
                    return;
                }
            }
        } catch (Exception e) {
            logger.error("MAP转换VO异常",e);
        }

    }
}

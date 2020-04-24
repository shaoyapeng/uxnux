package com.uxnux.activiti.utils;

import javax.persistence.Column;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @Author: 10785
 * @Date: 2019/8/23 17:35
 * @Version: 1.0
 */
public class ReflectUtil {


    private static Map fieldHashColumnMap;
    private static Map methodToColumnMap;
    private static Map resultMap;
    /**
     * 从get方法获取表字段名称
     * @param c
     * @return
     */
    public static Map getColumnForGetMethod (Class c) {
        methodToColumnMap = new LinkedHashMap();
        Method[] methods = c.getMethods();
        for (Method m: methods) {
            Annotation annotation = m.getAnnotation(Column.class);
            if (annotation != null) {
                methodToColumnMap.put(m.getName().toUpperCase(), ((Column) annotation).name());
            }
        }
        System.out.println("-------methodToColumnMap: " + methodToColumnMap);
        return methodToColumnMap;
    }

    /**
     * 获得实体属性和字段一一对应的关系map
     * @param c
     * @return
     */
    public static Map getFieldHashColumnMap (Class c) {
        fieldHashColumnMap = new LinkedHashMap();
        getColumnForGetMethod(c);
        Field[] fields = c.getDeclaredFields();
        if (methodToColumnMap == null || methodToColumnMap.size() < 1) {
            for (Field f: fields) {
                Annotation annotation = f.getAnnotation(Column.class);
                fieldHashColumnMap.put(f.getName(), ((Column) annotation).name());
            }
            return fieldHashColumnMap;
        }
        for (Field f: fields) {
            fieldHashColumnMap.put(f.getName(), methodToColumnMap.get("GET"+f.getName().toUpperCase()));
        }
        System.out.println("-------fieldHashColumnMap: " + fieldHashColumnMap);
        return fieldHashColumnMap;
    }

    /**
     * 将数据从Map中转成最后可以转成实体的map
     * @param dataMap
     * @return
     */
    public static Map reflect(Map dataMap) {
        resultMap = new LinkedHashMap();
        Iterator<Map.Entry<String, String>> iterator = fieldHashColumnMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getValue().toUpperCase();
            System.out.println("-------key: " + key);
            Object value = dataMap.get(key);
            if (value != null) {
                resultMap.put(entry.getKey(), value);
            }
        }
        System.out.println("-------resultMap: " + resultMap);
        return resultMap;
    }


//    /**
//     * 将Map转成对象
//     * @param c
//     * @param dataMap
//     * @param <T>
//     * @return
//     */
//    public static <T> T reflect(Class<T> c, Map dataMap) {
//        getFieldHashColumnMap(c);
//        reflect(dataMap);
//        return JSON.parseObject(JSON.toJSONString(resultMap), c);
//    }
//
//    /**
//     * 将list中的所有map转成对象
//     * @param c
//     * @param listData
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> reflect(Class<T> c, List<Map<String, Object>> listData) {
//        List<T> resultList = new ArrayList();
//        getFieldHashColumnMap(c);
//        for (Map dataMap: listData) {
//            reflect(dataMap);
//            resultList.add(JSON.parseObject(JSON.toJSONString(resultMap), c));
//        }
//        return resultList;
//    }


}

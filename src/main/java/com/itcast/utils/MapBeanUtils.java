package com.itcast.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class MapBeanUtils {
    /**
     * 通过内省将bean转化为map
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> beanToMap(Object bean) throws Exception {
        BeanInfo bi = Introspector.getBeanInfo(bean.getClass(), Object.class);
        PropertyDescriptor[] props = bi.getPropertyDescriptors();
        Map<String, Object> map = new HashMap<>();

        for (PropertyDescriptor prop : props) {
            String key = prop.getName();
            Object value = prop.getReadMethod().invoke(bean, null);
            map.put(key,value);
        }
        return map;
    }

    /**
     * 将map转化为javaBean
     */
    /*public static <T>T mapToBean(Map<String ,Object> map,Class<T> clz){
        return ;
    }*/


}

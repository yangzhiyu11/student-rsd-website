package com.rsd.util;

import com.rsd.bean.SysRole;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BeanUtil {
    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> map = new HashMap<>();
        try {
            Class clazz = bean.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    Object value = method.invoke(bean);
                    if (value != null) {
                        methodName = methodName.replace("get", "");

                        methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                        map.put(methodName, value);
                    }
                }
            }

        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Object mapToBean(Class clazz, Map<String, Object> map) {
        Object bean = null;
        try {
            bean = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    methodName = methodName.replace("set", "");
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                    method.invoke(bean, map.get(methodName));
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Object mapToBeanSpecial(Class clazz, Map<String, String[]> map) {
        Object bean = null;
        try {
            bean = clazz.newInstance();
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.startsWith("set")) {
                    methodName = methodName.replace("set", "");
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);

                    method.invoke(bean, map.get(methodName)[0]);
                }
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static Object cloneBean(Object sourceBean) {
        Object bean = null;
        try {
            Class clazz = sourceBean.getClass();
            bean = clazz.newInstance();

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("set")) {
                    Method sourceMethod = clazz.getMethod("g" + method.getName().substring(1));
                    Object value = sourceMethod.invoke(sourceBean);
                    method.invoke(bean, value);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static String beanToJson(Object obj) {
        Class clazz = obj.getClass();
        String zh = "";//最后的字符串
        try {
            Method[] methods = clazz.getMethods();//得到所有方法
            String temp = "";//临时的空字符串

            for (Method method : methods) {
                String methodName = method.getName();//得到所有的方法名
                if (methodName.startsWith("get") && !methodName.equals("getClass")) {
                    methodName = methodName.substring(3);
                    methodName = methodName.substring(0, 1).toLowerCase() + methodName.substring(1);
                    Object values = method.invoke(obj);
                    if (values != null) {
                        if (method.getReturnType().getName().equals(String.class.getName())) {
                            temp = "," + "\"" + methodName + "\"" + ":" + "\"" + values + "\"";
                        }
                        if (method.getReturnType().getName().equals(Integer.class.getName())) {
                            temp = "," + "\"" + methodName + "\"" + ":" + "" + values;
                        }
                        if (method.getReturnType().getName().equals(Date.class.getName())) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            temp = "," + "\"" + methodName + "\"" + ":" + "\"" + sdf.format(values) + "\"";
                        }
                        zh += temp;
                    }
                }
            }
            zh = "{" + zh.substring(1) + "}";//去掉最前面的逗号，两边加上花括号
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return zh;
    }

}

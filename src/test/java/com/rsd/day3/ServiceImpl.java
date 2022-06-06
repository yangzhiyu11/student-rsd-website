package com.rsd.day3;

import com.rsd.util.JDBCUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class ServiceImpl<E> implements IService<E>{

    private E e;

    @Override
    public void insert(E e) {
        String sql = JDBCUtil.insertSQL(e);
        JDBCUtil.execute(sql);
    }

    @Override
    public void update(E e) {
        String sql = JDBCUtil.updateSQL(e);
        JDBCUtil.execute(sql);
    }

    @Override
    public void delete(Integer id) {
        Class<E> clazz = getClassForGenericParadigm();
        Object obj = null;
        try {
            obj = clazz.newInstance();

            Method method = clazz.getMethod("setId", Integer.class);
            method.invoke(obj,id);
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        String sql = JDBCUtil.deleteSQL(obj);
        JDBCUtil.execute(sql);
    }

    @Override
    public List<E> queryList() {
        Class<E> clazz = getClassForGenericParadigm();
        String sql = JDBCUtil.selectSQL(clazz);
        return JDBCUtil.queryList(clazz,sql);
    }

    @Override
    public E getById(Integer id) {
        Class<E> clazz = getClassForGenericParadigm();
        String sql = JDBCUtil.selectSQL(clazz) + " where id=" + id;
        return (E) JDBCUtil.queryById(clazz,sql);
    }

    private Class<E> getClassForGenericParadigm() {
        Class<E> clazz = null;
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType)type;
            Type[] typeArguments = ptype.getActualTypeArguments();
            clazz = (Class<E>) typeArguments[0];
        }
        return clazz;
    }
}

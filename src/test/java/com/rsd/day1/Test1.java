package com.rsd.day1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Test1 {
    public static void main(String[] args) {
        Apple apple = new Apple();
        try {
            Class<?> clazz = Class.forName("com.rsd.day1.Apple");

            Field[] declaredFields = clazz.getDeclaredFields();//得到所有属性
            for (Field field : declaredFields) {
                String mod = Modifier.toString(field.getModifiers());
                field.setAccessible(true);//取消权限，私有的也能访问
                System.out.println(mod+"\t"+field.getType() + "\t" +field.getName()+ "\t" + field.get(apple));//getType看类型的,getModifiers看修饰符的
            }
            System.out.println("----------------------------------");

            Field[] fields = clazz.getFields();//得到所有可以被访问的属性
            for (Field field : fields) {
                String mod = Modifier.toString(field.getModifiers());
                field.setAccessible(true);//取消权限，私有的也能访问
                System.out.println(mod+"\t"+field.getType() + "\t" +field.getName()+ "\t" + field.get(apple));//getType看类型的,getModifiers看修饰符的
            }
            System.out.println("-------------------------------------------");

            Field field_id = clazz.getDeclaredField("id");
            System.out.println(field_id.getName());

            System.out.println("------------------------------------");

            Field field_id2 = clazz.getField("name");//只能访问公共的属性
            System.out.println(field_id2.getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

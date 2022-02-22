package com.originlang.base.reflect;

import java.lang.reflect.Field;

public class Reflect<T> {




    // 获取class对象
    public Class<?> getClazz(String clazz) {
        try {
            return Class.forName(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Class<?> getClazz(Module module, String clazz) {
        return Class.forName(module, clazz);
    }

    public Field[] getFields(Class<T> clazz) {
        return clazz.getFields();
    }

    //获得类的所有属性。包括私有的
    public Field[] getDeclaredFields(Class<T> clazz) {
        return clazz.getDeclaredFields();
    }


    public T newInstance(Class<T> clazz) {
        try {
//            //如果flag为true 则表示忽略访问权限检查!(可以访问任何权限的方法)
//            Constructor<T> constructor = clazz.getConstructor(clazz);
////            Constructor<T> constructor = clazz.getDeclaredConstructor();
////            constructor.setAccessible(true);
//            return constructor.newInstance();
          return   clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

    }
    // 会初始化
    public Object forName(String name) {
        try {
//            //如果flag为true 则表示忽略访问权限检查!(可以访问任何权限的方法)
//            Constructor<T> constructor = clazz.getConstructor(clazz);
////            Constructor<T> constructor = clazz.getDeclaredConstructor();
////            constructor.setAccessible(true);
//            return constructor.newInstance();
          return   Class.forName(name).getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }

    }


}

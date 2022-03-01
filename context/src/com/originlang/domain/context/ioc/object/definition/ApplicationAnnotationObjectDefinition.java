package com.originlang.domain.context.ioc.object.definition;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// 实现的benn定义
public class ApplicationAnnotationObjectDefinition extends AnnotationObjectDefinition {

    /**
     * 对象名称
     */
    public String objectName;
    //对象
    public Object object;

    public Class clazz;
    // 构造函数
    public Constructor[] constructorArray;
    //属性
//    public Field[] fieldArray;
    public List<Field> fieldList = new ArrayList<>(16);
    //方法
    Method[] methodArray;
    public List<Method> methodList = new ArrayList<>(16);

    @Override
    Annotation[] getAnnotationArray() {
        return this.annotationArray;
    }

    @Override
    void setAnnotationArray(Annotation[] annotationArray) {
        this.annotationArray = annotationArray;
    }


    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }


    public String getObjectName() {
        return this.objectName;
    }

    @Override
    public List<Field> getFieldList() {
        return this.fieldList;
    }

    @Override
    public List<Method> getMethodList() {
        return this.methodList;
    }

    @Override
    public Class getClazz() {
        return this.clazz;
    }

    @Override
    public Object newObject() {
        Object obj = null;
        try {
            return this.clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取对象失败");
    }
}

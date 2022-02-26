package com.originlang.domain.context.ioc.object.definition;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
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
    public List<Class<?>> fieldClazzList = new ArrayList<>(16);
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


    @Override
    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    @Override
    public String getObjectName() {
        return this.objectName;
    }
}

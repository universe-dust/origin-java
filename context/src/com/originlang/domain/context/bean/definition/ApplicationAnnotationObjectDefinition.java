package com.originlang.domain.context.bean.definition;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// 实现的benn定义
public class ApplicationAnnotationObjectDefinition extends AnnotationObjectDefinition {

    /**
     * 对象名称
     */
    String objectName;

    Class clazz;
    // 构造函数
    Constructor[] constructorArray;

    Field[] fieldArray;





    Method[] methodArray;

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

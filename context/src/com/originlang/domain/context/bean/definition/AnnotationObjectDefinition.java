package com.originlang.domain.context.bean.definition;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

// 抽象类有构造方法，但是不能实列化
public abstract class AnnotationObjectDefinition implements ObjectDefinition {

    Annotation[] annotationArray;


     abstract Annotation[] getAnnotationArray();

     abstract void setAnnotationArray(Annotation[] annotationArray);
}


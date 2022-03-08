package com.originlang.context.ioc.object.definition;


import java.lang.annotation.Annotation;

// 抽象类有构造方法，但是不能实列化
public abstract class AnnotationObjectDefinition implements ObjectDefinition {

    Annotation[] annotationArray;


     abstract Annotation[] getAnnotationArray();

     abstract void setAnnotationArray(Annotation[] annotationArray);
}


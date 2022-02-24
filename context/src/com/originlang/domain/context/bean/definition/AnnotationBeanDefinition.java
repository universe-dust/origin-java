package com.originlang.domain.context.bean.definition;


// 抽象类有构造方法，但是不能实列化
public abstract class AnnotationBeanDefinition implements BeanDefinition  {


    String beanName;

//    // 获得当前 Bean 的注解元数据
//    AnnotationMetadata getMetadata();
//
//    // 获得当前 Bean 的工厂方法上的元数据
//    MethodMetadata getFactoryMethodMetadata();
}

//class  AnnotationMetadata{
//
//}
//class  MethodMetadata{
//
//}
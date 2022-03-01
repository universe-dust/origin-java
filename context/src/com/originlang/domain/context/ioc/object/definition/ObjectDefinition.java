package com.originlang.domain.context.ioc.object.definition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface ObjectDefinition {

    // 单例、原型标识符
    String SCOPE_SINGLETON = "SCOPE_SINGLETON";
    String SCOPE_PROTOTYPE = "SCOPE_PROTOTYPE";





     Object newObject();

     String getObjectName();


     List<Field> getFieldList();

     List<Method> getMethodList();

     Class getClazz();

    // 标识 Bean 的类别，分别对应 用户定义的 Bean、来源于配置文件的 Bean、 内部的 Bean
//    int ROLE_APPLICATION = 0;
//    int ROLE_SUPPORT = 1;
//    int ROLE_INFRASTRUCTURE = 2;
//
//    // 设置、返回 Bean 的父类名称
//    void setParentName( String parentName);
//    String getParentName();
//

//
//    // 设置、返回 Bean 的作用域
//    void setScope( String scope);
//    String getScope();
//
//    // 设置、返回 Bean 是否懒加载
//    void setLazyInit(boolean lazyInit);
//    boolean isLazyInit();
//
//    // 设置、返回当前 Bean 所依赖的其它 Bean 名称。
//    void setDependsOn( String... dependsOn);
//    String[] getDependsOn();
//
//    // 设置、返回 Bean 是否可以自动注入。只对 @Autowired 注解有效
//    void setAutowireCandidate(boolean autowireCandidate);
//    boolean isAutowireCandidate();
//
//    // 设置、返回当前 Bean 是否为主要候选 Bean 。
//    // 当同一个接口有多个实现类时，通过该属性来配置某个 Bean 为主候选 Bean。
//    void setPrimary(boolean primary);
//    boolean isPrimary();
//
//    // 设置、返回创建该 Bean 的工厂类。
//    void setFactoryBeanName( String factoryBeanName);
//    String getFactoryBeanName();
//
//    // 设置、返回创建该 Bean 的工厂方法
//    void setFactoryMethodName( String factoryMethodName);
//    String getFactoryMethodName();
//
//    // 返回该 Bean 构造方法参数值、所有属性
////
////    ConstructorArgumentValues getConstructorArgumentValues();
////    MutablePropertyValues getPropertyValues();
//
//    // 返回该 Bean 是否是单例、是否是非单例、是否是抽象的
//    boolean isSingleton();
//    boolean isPrototype();
//    boolean isAbstract();
//
//    // 返回 Bean 的类别。类别对应上面的三个属性值。
//    int getRole();


}

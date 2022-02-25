package com.originlang.domain.aop;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切点
 * 作用在类或方法上
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PointCut {
//    //切入点的颗粒度需要到某个类下面的某个方法
//    /**
//     * 类名
//     */
//     String className;
//    /**
//     * 方法名
//     */
//     String methorName;

    // 对应切面的类全名： java.xxx.Abc
    String execution();

}



package com.originlang.context.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义一个切面，
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {


//    /**
//     * 增强逻辑
//     */
//      Advice advice;
//    /**
//     * 切入点
//     */
//      PointCut pointCut;

}




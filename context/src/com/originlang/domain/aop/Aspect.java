package com.originlang.domain.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 切面，
 */
@Target(ElementType.METHOD)
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




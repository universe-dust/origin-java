package com.originlang.domain.context.aop;

import java.lang.reflect.Method;

// aop通知类
public interface Advice {

    /**
     * 提供增强接口，参数需要谁？干什么？
     *
     * @param target 需要知道目标对象
     * @param method 需要知道目标方法
     * @param args   方法参数
     */
    Object invoke(Object target, Method method, Object[] args) throws Exception;

}

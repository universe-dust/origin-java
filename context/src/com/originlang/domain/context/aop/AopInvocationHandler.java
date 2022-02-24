package com.originlang.domain.context.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopInvocationHandler implements InvocationHandler {
    //目标对象
     Object target;
    //aop对象
     Aspect aspect;

    public AopInvocationHandler(Object bean, Aspect aspect) {
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //需要增强
        if (method.getName().matches(aspect.pointCut.methorName)) {
            return aspect.advice.invoke(target, method, args);
        }
        return method.invoke(target, args);
    }
}


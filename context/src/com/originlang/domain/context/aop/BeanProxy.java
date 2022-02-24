package com.originlang.domain.context.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BeanProxy {

    public static void proxy() {
        new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("proxy");
                return null;
            }
        };
    }
}

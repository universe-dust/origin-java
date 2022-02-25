package com.originlang.example.entity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AopSay implements InvocationHandler {
    //用于依赖注入
    public AopSay(){

    }

    public   Object target;

    public AopSay(Object userAopInterface){
        this.target = userAopInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理say111111111111111111111113333");
        Object obj = null;
        try {
             obj = method.invoke(target, args);
        }catch (Exception e){
            System.out.println("代理say22222222222222222222222222异常");
        }
        System.out.println("代理say22222222222222222222222222");
        return obj;
    }
}

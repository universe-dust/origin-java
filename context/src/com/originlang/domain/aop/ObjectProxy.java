package com.originlang.domain.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ObjectProxy {


    public  Object newProxyInstance(Class clazz, InvocationHandler invocationHandler) {
        System.out.println("---------------------ObjectProxy创建代理对象");
        // interfaceClazz 只能是接口
            //类加载器，
     Object objectProxy  =    Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},invocationHandler);



//        new InvocationHandler(){
//
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                System.out.println("proxy");
//                return null;
//            }
//        };

        return objectProxy;
    }
}

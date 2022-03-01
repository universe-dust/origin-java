package com.originlang.domain.aop;

import com.originlang.domain.aop.annotation.PointCut;
import com.originlang.domain.context.ioc.object.definition.ObjectDefinition;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 代理对象工厂
 */
public class ApplicationProxyObjectFactory {


    public Map newProxyObject(ObjectDefinition objectDefinition) {
        Object object = objectDefinition.getObject();
        //获取代理的方法
        Class clazz = objectDefinition.getClazz();

        // 判断是否有代理方法，如果有则返回代理对象
        Method[] methodArray = clazz.getMethods();

        Map<String, Object> proxyMap = new ConcurrentHashMap<>(16);
        Object proxyInstance = null;
        String execution = null;
        for (Method method : methodArray) {
            PointCut pointCutAnnotation = method.getAnnotation(PointCut.class);
            if (pointCutAnnotation == null) {
                continue;
            }
            //配置的代理类
            execution = pointCutAnnotation.execution();
            System.out.println("切点接口：" + execution);
            Class executionClazz = null;
            Object aspect = null;
            Method before = null;
            Method after = null;
            try {
                executionClazz = Class.forName(execution);
                try {
                    aspect = executionClazz.getConstructor().newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            String superInterface = pointCutAnnotation.superInterface();
            Class superClazz = null;
            try {
                superClazz = Class.forName(superInterface);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (!"".equals(superInterface)) {
                // jdk动态代理
                proxyInstance = doNewJdkDynamicProxyObject(object, aspect, before, after, superClazz);


            }


//            System.out.println("----------------------class interface" + clazz.getInterfaces()[0]);
//            //object是被代理对象
//            Object proxyInstance = null;
//                try {
//
//                    proxyInstance = jdkDynamicProxyObject.newProxyInstance(clazz.getInterfaces()[0], (InvocationHandler) executionClazz.getConstructor(Object.class).newInstance(object));
//
//                } catch (InstantiationException e) {
//                    // todo
//                    e.printStackTrace();
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                } catch (InvocationTargetException e) {
//                    e.printStackTrace();
//                } catch (NoSuchMethodException e) {
//                    e.printStackTrace();
//                }
//            //注入代理对象
//            System.out.println(clazz.getName() + "----------------------------------------------------------Proxy instance=" + proxyInstance);
////                    ApplicationContext.registerSingleton(clazz.getInterfaces()[0].getName(),proxyInstance);
        }
        proxyMap.put(execution, proxyInstance);
        return proxyMap;


    }


    private Object doNewJdkDynamicProxyObject(Object target, Object aspect, Method before, Method after, Class interfaceClazz) {
        JdkDynamicProxyObject jdkDynamicProxyObject = new JdkDynamicProxyObject(target, aspect, before, after);
        return jdkDynamicProxyObject.newProxyInstance(interfaceClazz);

    }


}

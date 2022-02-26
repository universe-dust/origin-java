package com.originlang.domain.context.ioc;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.originlang.domain.aop.ObjectProxy;
import com.originlang.domain.aop.PointCut;
import com.originlang.domain.context.ioc.object.factory.ObjectFactory;

public class ApplicationContext extends ApplicationAbstractContext {

    private ApplicationContext() {

    }

    private ApplicationContext applicationContext = new ApplicationContext();



    // 三级缓存



//    public <T> T registerSingleton(String key, T value) {
//        return (T) applicationContext.singletonObjects.put(key, value);
//    }
//
//
//
//    public static <T> T getDependencyByClass(Class<T> clazz) {
//        return (T) getDependencyByName(clazz.getName());
//    }
//
//
//    public synchronized Object getDependencyByName(String classFullName) {
//        Object object = singletonObjects.get(classFullName);
//        if (object == null) {
//            //依赖注入
////            dependencyInjection(classFullName);
//        }
//
//        return object;
//    }





}

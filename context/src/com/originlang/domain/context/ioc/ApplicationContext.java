package com.originlang.domain.context.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.originlang.domain.context.bean.factory.ObjectFactory;

public class ApplicationContext extends ApplicationAbstractContext {




    private  static ApplicationContext applicationContext = new ApplicationContext();

    // 三级缓存
    private  Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
    private  Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    private  Map<String, ObjectFactory> singletonFactories = new HashMap<>(16);




    private ApplicationContext(){

    }




    public static  <T>T  registerSingleton(String key ,T value){
          return (T) applicationContext.singletonObjects.put(key,value);
    }

    public static Object getDependencyByName(String className){
        return  applicationContext.singletonObjects.get(className);
    }
//    public <T> T getDependencyByName(String className,int i){
//        return (T) applicationContext.singletonObjects.get(className);
//    }
    public static   <T> T  getDependencyByClass(Class<T> clazz){
        return (T)  applicationContext.singletonObjects.get(clazz.getName());
    }





}

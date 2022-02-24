package com.originlang.domain.context.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.originlang.domain.context.bean.factory.ObjectFactory;

public class ApplicationContext extends ApplicationAbstractContext {


    private ApplicationContext(){

    }

    private  static ApplicationContext applicationContext = new ApplicationContext();

    // 从上至下 分表代表这“三级缓存”
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

    //单例的IoC容器缓存
    private Map<String,Object> factoryBeanObjectSingletonCacheMap = new ConcurrentHashMap<String, Object>();
    //







    public static  Object  put(String key ,Object value){
          return  applicationContext.singletonObjects.put(key,value);
    }





}

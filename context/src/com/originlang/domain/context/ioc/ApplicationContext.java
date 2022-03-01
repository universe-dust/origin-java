package com.originlang.domain.context.ioc;

import java.lang.reflect.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.originlang.domain.aop.ApplicationProxyObjectFactory;
import com.originlang.domain.context.ioc.object.definition.ObjectDefinition;

/**
 * 应用上下文，
 * ioc容器；
 * 环境；
 */

public class ApplicationContext extends ApplicationAbstractContext {

    private ApplicationContext() {

    }

    private static ApplicationContext applicationContext = new ApplicationContext();

    private Map<String, Object> iocContainerMap = new ConcurrentHashMap<>(256);

    private Map<String, Object> earlyObjectContainerMap = new ConcurrentHashMap<>(16);

    private Map<String, ObjectDefinition> objectDefinitionMap = new ConcurrentHashMap<>(256);


    /**
     * 获取应用上下文
     *
     * @return 应用上下文
     */
    public static ApplicationContext getInstance() {
        return applicationContext;
    }

    @Override
    public Object getObject(String clazzName) {
        System.out.println("***************getobj=" + clazzName);
        Object object = iocContainerMap.get(clazzName);
        if (object != null) {
            return object;
        }
//            //没有创建，
        ObjectDefinition objectDefinition = objectDefinitionMap.get(clazzName);
//        objectDefinitionMap.remove(name);
//        earlyObjectContainerMap.put(name,objectDefinition.newObject());
//        List<Field> fieldList = objectDefinition.getFieldList();
//
//        // aop处理
//        List<Method> methodList = objectDefinition.getMethodList();


        return object;
    }

    public Map<String, ObjectDefinition> getObjectDefinitionMap() {
        return objectDefinitionMap;
    }

    /**
     * 对声明的属性进行依赖注入；
     * 要注入的全类名， java.xxx.Abc
     */
    // todo ，对ObjectDefinition注入ioc容器    Dependency Injection
    public void dependencyInjection() {
        //创建早期对象

        try {
            //            objectDefinitionMap.forEach((clazzName,objectDefinition)->{
            //                doDependencyInjection(objectDefinition);
            //            });
            for (Map.Entry<String, ObjectDefinition> entry : objectDefinitionMap.entrySet()) {
                ObjectDefinition objectDefinition = entry.getValue();
                doDependencyInjection(objectDefinition);
            }


        } catch (IllegalAccessException e) {
            //todo
            e.printStackTrace();
        }


    }


    private void doDependencyInjection(ObjectDefinition objectDefinition) throws IllegalAccessException {
        Object object = objectDefinition.newObject();
        if (object == null) {
            throw new RuntimeException("依赖注入对象不能为null");
        }

       String clazzName =null;
       var clazzInterfaceArray = objectDefinition.getClazz().getInterfaces();
       if(clazzInterfaceArray==null||clazzInterfaceArray.length==0){
            clazzName = objectDefinition.getObjectName();
       }else {
           clazzName=  clazzInterfaceArray[0].getName();
       }


        System.out.println("依赖注入对象" + clazzName);

        earlyObjectContainerMap.put(clazzName, object);

        List<Field> fieldList = objectDefinition.getFieldList();


        for (Field field : fieldList) {
            //属性的全名
            Class[] fieldClazzArray = field.getType().getInterfaces();
            String fieldClazzName = null;
            if (fieldClazzArray == null || fieldClazzArray.length == 0) {
                fieldClazzName = field.getType().getName();
            } else {
                fieldClazzName = fieldClazzArray[0].getName();
            }

            Object fieldObject = iocContainerMap.get(fieldClazzName);
            if (fieldObject == null) {
                fieldObject = earlyObjectContainerMap.get(fieldClazzName);
            }

            if (fieldObject == null) {
                ObjectDefinition fiedlObjectDefinition = objectDefinitionMap.get(fieldClazzName);
                fieldObject = fiedlObjectDefinition.newObject();
                earlyObjectContainerMap.put(fieldClazzName, fieldObject);
            }
            try {

                field.set(object, fieldObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // aop处理
        List<Method> methodList = objectDefinition.getMethodList();
        for (Method method : methodList) {
            ApplicationProxyObjectFactory factory = new ApplicationProxyObjectFactory();
            Map<String, Object> proxyObject = factory.newProxyObject(objectDefinition);
            iocContainerMap.putAll(proxyObject);
        }
        objectDefinitionMap.remove(clazzName);
        earlyObjectContainerMap.remove(clazzName);
        iocContainerMap.put(clazzName, object);
    }


//    public <T> T registerSingleton(String key, T value) {
//        return (T) applicationContext.singletonObjects.put(key, value);
//    }


    public static <T> T getDependencyByClass(Class<T> clazz) {
        return (T) applicationContext.iocContainerMap.get(clazz.getName());
    }


}

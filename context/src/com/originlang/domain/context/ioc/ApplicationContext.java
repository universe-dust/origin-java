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

    private Map<String ,ObjectDefinition> objectDefinitionMap = new ConcurrentHashMap<>(256);


    /**
     * 获取应用上下文
     *
     * @return 应用上下文
     */
    public static ApplicationContext getInstance() {
        return applicationContext;
    }

    @Override
    public Object getObject(String name) {
        System.out.println("***************getobj="+name);
        Object object = iocContainerMap.get(name);
//        if (object != null) {
//            return object;
//        }
//            //没有创建，
//           ObjectDefinition objectDefinition = objectDefinitionMap.get(name);
//        objectDefinitionMap.remove(name);
//        earlyObjectContainerMap.put(name,objectDefinition.newObject());
//        List<Field> fieldList = objectDefinition.getFieldList();
//
//        // aop处理
//        List<Method> methodList = objectDefinition.getMethodList();


        return object;
    }

    public Map<String, ObjectDefinition> getObjectDefinitionMap(){
        return objectDefinitionMap;
    }

    /**
     * 对声明的属性进行依赖注入；
     * 要注入的全类名， java.xxx.Abc
     */
    // todo ，对ObjectDefinition注入ioc容器    Dependency Injection
    public void dependencyInjection() {
        for (Map.Entry<String,ObjectDefinition> entry: objectDefinitionMap.entrySet() ) {
            ObjectDefinition objectDefinition = entry.getValue();
            try {
                doDependencyInjection(objectDefinition);
            } catch (IllegalAccessException e) {
                //todo
                e.printStackTrace();
            }
        }

    }


    private void  doDependencyInjection(ObjectDefinition objectDefinition) throws IllegalAccessException {
        Object object = objectDefinition.newObject();
        if (object == null) {
            throw new RuntimeException("依赖注入对象不能为null");
        }
       String clazzName = objectDefinition.getObjectName();
        System.out.println("依赖注入对象"+clazzName);
        objectDefinitionMap.remove(clazzName);
        earlyObjectContainerMap.put(clazzName, object);

        List<Field> fieldList = objectDefinition.getFieldList();


        for(Field field:fieldList){
            //属性的全名
            String fieldClazzName = field.getType().getName();
            Object fieldObject =  earlyObjectContainerMap.get(fieldClazzName);
            ObjectDefinition fiedlObjectDefinition = objectDefinitionMap.get(fieldClazzName);
            if(fieldObject==null){
                 fieldObject = fiedlObjectDefinition.newObject();
                earlyObjectContainerMap.put(fieldClazzName,fieldObject);
            }
            try {

                field.set(object,fieldObject);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        // aop处理
        List<Method> methodList = objectDefinition.getMethodList();
        for (Method method:methodList){
            ApplicationProxyObjectFactory factory =new ApplicationProxyObjectFactory();
            Map<String,Object> proxyObject = factory.newProxyObject(objectDefinition);
            iocContainerMap.putAll(proxyObject);
        }
        earlyObjectContainerMap.remove(clazzName);
        iocContainerMap.put(clazzName,object);
    }



//    public <T> T registerSingleton(String key, T value) {
//        return (T) applicationContext.singletonObjects.put(key, value);
//    }


    public static <T> T getDependencyByClass(Class<T> clazz) {
        return (T) applicationContext.iocContainerMap.get(clazz.getName());
    }





}

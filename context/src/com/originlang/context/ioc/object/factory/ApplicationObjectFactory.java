package com.originlang.context.ioc.object.factory;


import com.originlang.context.aop.annotation.PointCut;
import com.originlang.context.annotation.Dependency;
import com.originlang.context.ioc.object.definition.ApplicationAnnotationObjectDefinition;
import com.originlang.context.ioc.object.definition.ObjectDefinition;

import java.lang.reflect.*;
import java.util.*;

/**
 * 对象工厂
 */
public class ApplicationObjectFactory implements ObjectFactory {


//    private Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);
//    private Map<String, Object> earlySingletonObjects = new HashMap<>(16);
//    private Map<String, ApplicationAnnotationObjectDefinition> singletonFactories = new HashMap<>(16);

    /**
     * Names of beans that are currently in creation.
     */
    // 这个缓存也十分重要：它表示bean创建过程中都会在里面呆着~
    // 它在Bean开始创建时放值，创建完成时会将其移出~
//    private final Set<String> singletonsCurrentlyInCreation = Collections.newSetFromMap(new ConcurrentHashMap<>(16));

    /**
     * Names of beans that have already been created at least once.
     */
    // 当这个Bean被创建完成后，会标记为这个 注意：这里是set集合 不会重复
    // 至少被创建了一次的  都会放进这里~~~~
//    private final Set<String> alreadyCreated = Collections.newSetFromMap(new ConcurrentHashMap<>(256));


//    protected Object getSingleton(String beanName, boolean allowEarlyReference) {
//        Object singletonObject = this.singletonObjects.get(beanName);
//        if (singletonObject == null && isSingletonCurrentlyInCreation(beanName)) {
//            synchronized (this.singletonObjects) {
//                singletonObject = this.earlySingletonObjects.get(beanName);
//                if (singletonObject == null && allowEarlyReference) {
//                    ObjectFactory singletonFactory = this.singletonFactories.get(beanName);
//                    if (singletonFactory != null) {
////                        singletonObject = singletonFactory.getObject();
//                        this.earlySingletonObjects.put(beanName, singletonObject);
//                        this.singletonFactories.remove(beanName);
//                    }
//                }
//            }
//        }
//        return singletonObject;
//    }
//    public boolean isSingletonCurrentlyInCreation(String beanName) {
//        return this.singletonsCurrentlyInCreation.contains(beanName);
//    }
//    protected boolean isActuallyInCreation(String beanName) {
//        return isSingletonCurrentlyInCreation(beanName);
//    }

    /**
     * 类全名，创建对象
     *
     * @param classNameList ，
     * @throws ClassNotFoundException
     * @return
     */
    @Override
    public Map<String, ObjectDefinition> createObject(List<String> classNameList, Map<String, ObjectDefinition> collection) {

        try {
            doCreateObject(classNameList, collection);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return collection;
    }


    private Map<String, ObjectDefinition> doCreateObject(List<String> classNameList, Map<String, ObjectDefinition> map) throws ClassNotFoundException {

        for (String classFullName : classNameList) {

            System.out.println(classFullName);

            Class clazz = Class.forName(classFullName);

//                    Object o =    c.getDeclaredConstructor().newInstance();
//                 Object o2 =     Thread.currentThread().getContextClassLoader().loadClass(e).getDeclaredConstructor().newInstance();
            // 待优化，使用自己的类加载器，目前想到使用回调函数实现 todo
//               clazz.isLocalClass()

            // 抽象类，接口
            if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }


            //无参构造方法
            Constructor clazzConstructor = null;
            try {
                // getDeclaredConstructor 会获取到私有构造方法， constructor.setAccessible(true);
                clazzConstructor = clazz.getConstructor();
            } catch (NoSuchMethodException e) {

                e.printStackTrace();

            } finally {
                if (clazzConstructor == null) {
                    // public无参构造方法不存在,抛出异常
                    throw new RuntimeException("public无参构造方法不存在,class:" + classFullName);
                }
            }


            //获取 声明的依赖
            Dependency dependencyAnnotation = (Dependency) clazz.getAnnotation(Dependency.class);
            if (dependencyAnnotation == null) {
                //没有声明的不注入
                continue;
            }
            //创建
            ApplicationAnnotationObjectDefinition objectDefinition = new ApplicationAnnotationObjectDefinition();
            objectDefinition.setObjectName(classFullName);
            objectDefinition.clazz = clazz;
            objectDefinition.constructorArray = clazz.getConstructors();


            Object object = null;
            try {
                //先提前暴漏出对象
                object = clazzConstructor.newInstance();
                objectDefinition.object = object;

            } catch (InstantiationException e) {
                // todo
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            Field[] fieldArray = clazz.getFields();
            for (Field field : fieldArray) {
                Dependency fieldDependencyAnnotation = field.getAnnotation(Dependency.class);
                if (fieldDependencyAnnotation == null) {
                    //不需要注入的
                    continue;
                }
                String fieldName = field.getName();
                Class fieldClazz = field.getType();
                System.out.println(fieldName + "*********************" + fieldClazz.getName());
                objectDefinition.fieldList.add(field);
            }


            // 判断是否有代理方法，如果有则返回代理对象
            Method[] methodArray = clazz.getMethods();
            for (Method method : methodArray) {
                PointCut pointCutAnnotation = method.getAnnotation(PointCut.class);
                if (pointCutAnnotation == null) {
                    continue;
                }

                objectDefinition.methodList.add(method);
//                //配置的代理类
//                String execution = pointCutAnnotation.execution();
//                System.out.println("切点：" + execution);
//                Class executionClazz = Class.forName(execution);
//                JdkDynamicProxyObject jdkDynamicProxyObject = new JdkDynamicProxyObject();
//
//                System.out.println("----------------------class interface" + clazz.getInterfaces()[0]);
//                //object是被代理对象
//                Object proxyInstance = null;
//                try {
//
////                    proxyInstance = jdkDynamicProxyObject.newProxyInstance(clazz.getInterfaces()[0], (InvocationHandler) executionClazz.getConstructor(Object.class).newInstance(object));
////
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
                //注入代理对象
//                System.out.println(clazz.getName() + "----------------------------------------------------------Proxy instance=" + proxyInstance);
//                    ApplicationContext.registerSingleton(clazz.getInterfaces()[0].getName(),proxyInstance);
            }

            //如果有接口用接口名称
            Class[] interfaceArray = clazz.getInterfaces();

            if(interfaceArray!=null&& interfaceArray.length!=0) {

                classFullName = interfaceArray[0].getName();
            }

            map.put(classFullName,objectDefinition);

        }

        return map;
    }





    /**
     * 创建单例
     *
     * @param clazz
     */
//    private ApplicationAnnotationObjectDefinition newSingleton(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        //公共无参
//        Constructor clazzConstructor = clazz.getConstructor();
//        //先创建对象，放入创建中的缓存中
//        Object clazzObject = clazzConstructor.newInstance();
//
//
//        //属性,只获取public
//        Field[] fieldArray = clazz.getFields();
//        for (Field field : fieldArray) {
////                    field.getAnnotation();
//            String fieldName = field.getName();
//            Class fieldClazz = field.getType();
//            System.out.println(fieldName + "*********************" + fieldClazz.getName());
//        }
//
//        //方法,只获取public
//        Method[] methodArray = clazz.getMethods();
//    }
    public boolean checkDependency(Class clazz) {
        if (clazz == null) {
            return false;
        }

        // 跳过抽象类，接口
        if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
            return false;
        }
        //类注解
        Dependency dependency = (Dependency) clazz.getAnnotation(Dependency.class);
        if (dependency == null) {
            //未声明注入的跳过
            return false;
        }

        //构造函数,只获取public
        try {
            Constructor clazzConstructor = clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            // todo
            e.printStackTrace();
            //没有公共无参构造函数
            return false;
        }
        return true;


    }

    /**
     * 校验对象是否可以创建，
     */
    public boolean checkDependency(String classFullName) {
        if (classFullName == null) {
            return false;
        }
        Class clazz = null;
        try {
            clazz = Class.forName(classFullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // todo
            return false;
        }
        return checkDependency(clazz);
    }


}


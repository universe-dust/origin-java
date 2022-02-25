package com.originlang.domain.context.ioc;

import com.originlang.domain.aop.ObjectProxy;
import com.originlang.domain.aop.PointCut;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;

//依赖注入类
public class ApplicationDependencyInjection implements DependencyInjection {


    /**
     * @param classNameList 要注入的全类名， java.xxx.Abc
     */
    public void dependencyInjection(List classNameList) {
        doDependencyInjection(classNameList);
    }


    private void doDependencyInjection(List<String> classNameList) {

        for (String className : classNameList) {
            System.out.println(className);
            try {
                Class clazz = Class.forName(className);

//                    Object o =    c.getDeclaredConstructor().newInstance();
//                 Object o2 =     Thread.currentThread().getContextClassLoader().loadClass(e).getDeclaredConstructor().newInstance();
                // 待优化，使用自己的类加载器，目前想到使用回调函数实现 todo
//               clazz.isLocalClass()

                // 抽象类，接口
                if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
                    continue;
                }


                Field[] fieldArray = clazz.getFields();
                for (Field field : fieldArray) {
//                    field.getAnnotation();
                    String fieldName = field.getName();
                    Class fieldClazz = field.getType();
                    System.out.println(fieldName + "*********************" + fieldClazz.getName());
                }







                // getDeclaredConstructor 会获取到私有构造方法， constructor.setAccessible(true);
                Constructor constructor = clazz.getClassLoader().loadClass(className).getConstructor();
                // 没有public修饰的构造方法
                if (constructor == null) {
                    continue;
                }



                Object obj = constructor.newInstance();
                //放入容器
                ApplicationContext.registerSingleton(className, obj);

                System.out.println(obj);




                // 判断是否有代理方法，如果有则返回代理对象
                Method[] methodArray = clazz.getMethods();
                for(Method method:methodArray){
                    PointCut pointCutAnnotation = method.getAnnotation(PointCut.class);
                    if(pointCutAnnotation==null){
                        continue;
                    }
                    String execution =  pointCutAnnotation.execution();
                    System.out.println("切点："+execution);
                    Class executionClazz = Class.forName(execution);
                    ObjectProxy objectProxy = new ObjectProxy();
//                    clazz.getSuperclass();
                    System.out.println("----------------------class interface"+ clazz.getInterfaces()[0]);
                    Object proxyInstance = objectProxy.newProxyInstance(clazz.getInterfaces()[0],(InvocationHandler) executionClazz.getConstructor(Object.class).newInstance(obj));
                    //注入代理对象
                    System.out.println(clazz.getName() +"----------------------------------------------------------Proxy instance=" + proxyInstance);
                    ApplicationContext.registerSingleton(clazz.getInterfaces()[0].getName(),proxyInstance);
                }

            } catch (Exception ex) {
                // todo 异常处理
                ex.printStackTrace();
            }
        }


//        classNameList.stream().forEach(className -> {
//
//
//        });
    }


}

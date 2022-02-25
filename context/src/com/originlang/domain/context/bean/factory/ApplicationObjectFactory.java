package com.originlang.domain.context.bean.factory;


import com.originlang.domain.context.annotation.Dependency;
import com.originlang.domain.context.bean.definition.ObjectDefinition;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ApplicationObjectFactory implements ObjectFactory {

    private List<ObjectDefinition> objectDefinitionList ;

    /**
     * 类全名，创建对象
     * @param classNameList ，
     * @throws ClassNotFoundException
     */
    @Override
    public void createObject(List<String> classNameList) throws ClassNotFoundException {
        objectDefinitionList = new ArrayList<>(classNameList.size());
        doCreateObject(classNameList);
    }



    private void doCreateObject(List<String> classNameList) throws ClassNotFoundException {
        for (String className : classNameList) {
            Class clazz = Class.forName(className);


            // 跳过抽象类，接口
            if (clazz.isInterface() || Modifier.isAbstract(clazz.getModifiers())) {
                continue;
            }
            //类注解
            Dependency dependency = (Dependency) clazz.getAnnotation(Dependency.class);
            if(dependency==null){
                //未声明注入的跳过
                continue;
            }

            // todo 实现其它的




            //构造函数,只获取public
            Constructor[] clazzConstructor = clazz.getConstructors();


            //属性,只获取public
            Field[] fieldArray = clazz.getFields();

            //方法,只获取public
            Method[] methodArray = clazz.getMethods();


        }

    }


    private void handleAnnotation(Class clazz){
        Dependency dependency = (Dependency) clazz.getAnnotation(Dependency.class);

        //没有标记  @DependencyInjection 的不创建




    }


}


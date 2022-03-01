package com.originlang.example;

import com.originlang.domain.context.ApplicationBootstrap;
import com.originlang.domain.context.annotation.Application;
import com.originlang.domain.context.annotation.Dependency;
import com.originlang.domain.context.ioc.ApplicationContext;
import com.originlang.example.entity.User;
import com.originlang.example.entity.UserAopInterface;


import java.lang.reflect.Field;


@Application
public class AppRun {

    public static void main(String[] args) throws ClassNotFoundException {

//        Class<?> cls = Class.forName("com.originlang.base.SystemInfo");
//        Field[] fields = cls.getDeclaredFields();
//        for (Field field : fields) {
//            printFieldValue(field);
//        }

        Class userClass=  Class.forName("com.originlang.example.entity.User");
        System.out.println(userClass.getAnnotation(Dependency.class));
        System.out.println(userClass.getDeclaredAnnotation(Dependency.class));
       Field[] fields2 = userClass.getDeclaredFields();
        for (Field field : fields2) {
//            printFieldValue(field);
//            field.set(account);
        }


        ApplicationBootstrap.run(AppRun.class,args);

        System.out.println("---------------------------------");

//        System.out.println(ApplicationContext.getDependencyByName("com.originlang.example.entity.User"));

        //有代理类则获取不到此方法
//        System.out.println(ApplicationContext.getDependencyByClass(User.class).say());
        UserAopInterface userAopInterface = (UserAopInterface) ApplicationContext.getInstance().getObject("com.originlang.example.entity.UserAopInterface");
        System.out.println(userAopInterface.aopSay("aop ssssssssssssssssss"));
    }


    public static void printFieldValue(Field field) {
        String fieldName = field.getName();
        try {
            // Make the field accessible, in case it is not accessible
            // based on its declaration such as a private field
            field.setAccessible(true);
            // Print the field's value
            System.out.println(fieldName + " = " );
            System.out.println(field.getAnnotation(Dependency.class));

        } catch (Exception  e) {
            System.out.println("Accessing " + fieldName +
                    ". Error: " + e.getMessage());
        }
    }

}

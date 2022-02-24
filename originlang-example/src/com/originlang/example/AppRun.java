package com.originlang.example;

import com.originlang.domain.context.ApplicationBootstrap;
import com.originlang.domain.context.annotation.Application;


import java.lang.reflect.Field;
import java.lang.reflect.InaccessibleObjectException;


@Application
public class AppRun {

    public static void main(String[] args) throws ClassNotFoundException {

//        Class<?> cls = Class.forName("com.originlang.base.SystemInfo");
//        Field[] fields = cls.getDeclaredFields();
//        for (Field field : fields) {
//            printFieldValue(field);
//        }



        ApplicationBootstrap.run(AppRun.class,args);
    }


    public static void printFieldValue(Field field) {
        String fieldName = field.getName();
        try {
            // Make the field accessible, in case it is not accessible
            // based on its declaration such as a private field
            field.setAccessible(true);
            // Print the field's value
            System.out.println(fieldName + " = " + field.get(null));
        } catch (IllegalAccessException | IllegalArgumentException |
                InaccessibleObjectException e) {
            System.out.println("Accessing " + fieldName +
                    ". Error: " + e.getMessage());
        }
    }

}

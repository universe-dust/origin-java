package com.originlang.example;

import com.originlang.base.reflect.Reflect;
import com.originlang.domain.context.annotation.Domain;
import com.originlang.example.entity.User;


public class Example {

    public static void main(String[] args)   {


        Reflect<User> reflect = new Reflect<User>();




        Class c  = reflect.getClazz("com.originlang.example.User");
        System.out.println(c);
        System.out.println(User.class.getAnnotation(Domain.class));
        try {
            User user = (User) c.getConstructor().newInstance();
            System.out.println(user);
//           Domain domain =     c.getAnnotation(Domain.class);
//            System.out.println(annotation);



        }catch (Exception e){}






        // todo 设置日志级别
//       LogFacade logFacade = LogFactory.getLogger();
//       logFacade.info("test");

    }

}

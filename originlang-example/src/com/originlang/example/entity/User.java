package com.originlang.example.entity;

import com.originlang.context.aop.annotation.PointCut;
import com.originlang.context.annotation.Dependency;
import com.originlang.context.annotation.Entity;
import com.originlang.example.service.UserService;

import java.util.List;

@Dependency
@Entity
public class User implements UserAopInterface{

    private Long id =123L;
    private  Integer age;
    private  String name;
    private List<String> bookList;

    @Dependency
    public Account account;

    @Dependency
    public UserService userService;

    public String say(){
        System.out.println("----------------user say");
        userService.userLogin();
        return "say";
    }

    @PointCut(execution = "com.originlang.example.entity.AopSay",superInterface ="com.originlang.example.entity.UserAopInterface" )
    public  String aopSay(String say){
        System.out.println("----------------user say"+say);
        return say;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}

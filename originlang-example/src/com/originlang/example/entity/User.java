package com.originlang.example.entity;

import com.originlang.domain.aop.annotation.PointCut;
import com.originlang.domain.context.annotation.Dependency;
import com.originlang.domain.context.annotation.Entity;

import java.util.List;

@Entity
public class User implements UserAopInterface{

    private Long id =123L;
    private  Integer age;
    private  String name;
    private List<String> bookList;

    @Dependency
    private Account account;

    public String say(){
        System.out.println("----------------user say");
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

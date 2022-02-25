package com.originlang.example.entity;

import com.originlang.domain.aop.PointCut;
import com.originlang.domain.context.annotation.DependencyInjection;
import com.originlang.domain.context.annotation.Entity;

import java.util.List;

@Entity
public class User implements UserAopInterface{

    private Long id =123L;
    private  Integer age;
    private  String name;
    private List<String> bookList;

    @DependencyInjection
    private Account account;

    public String say(){
        System.out.println("----------------user say");
        return "say";
    }

    @PointCut(execution = "com.originlang.example.entity.AopSay")
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

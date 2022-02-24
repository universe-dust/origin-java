package com.originlang.example.entity;

import com.originlang.domain.context.annotation.DependencyInjection;
import com.originlang.domain.context.annotation.Domain;
import com.originlang.domain.context.annotation.Entity;

import java.util.List;

@Domain
public class User {

    private Long id =123L;
    private  Integer age;
    private  String name;
    private List<String> bookList;

    @DependencyInjection
    private Account account;

    public void say(){
        System.out.println("----------------user say");
    }
    public static void say(String say){
        System.out.println("----------------user say"+say);
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

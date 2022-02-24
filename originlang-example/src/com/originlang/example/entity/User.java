package com.originlang.example.entity;

import com.originlang.domain.context.annotation.Domain;

import java.util.List;

@Domain
public class User {

    private Long id;
    private  Integer age;
    private  String name;
    private List<String> bookList;



}

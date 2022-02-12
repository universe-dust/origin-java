package com.originlang.server;


import java.util.List;

// 容器的抽象，对象tomcat实列
public class Server {

    private List<Service> serviceList;


    //    public static void run(Class clazz){
//    }
    public static void run(Class clazz,String[] args){
        refresh( clazz, args);
    }

//
//    public static void main(String[] args) {
//
//        refresh( clazz, args);
//
//    }


    private static void refresh(Class clazz,String[] args){
        System.out.println("refresh");
    }

}

package com.originlang.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;



public class TransactionInvocationHandler implements InvocationHandler{

    private Object target;

    public TransactionInvocationHandler(){

    }

    public TransactionInvocationHandler(Object target){

        this.target = target;

    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



        Object obj = null;

        try{
//            session = SqlSessionUtil.getSqlSession();

            obj = method.invoke(target, args);

//            session.commit();
        }catch(Exception e){
//            session.rollback();
            e.printStackTrace();

            //处理的是什么异常，继续往上抛什么异常
            throw e.getCause();

        }finally{
//            SqlSessionUtil.myClose(session);
        }

        return obj;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this);

    }

}













































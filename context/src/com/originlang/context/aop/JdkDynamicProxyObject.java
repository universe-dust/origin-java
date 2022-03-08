package com.originlang.context.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理对象
 */
public class JdkDynamicProxyObject implements InvocationHandler,ProxyObject{

    //被代理的对象
    public Object target;

    //切面
    public Object aspect;

    public Method before;
    public Method after;

    public JdkDynamicProxyObject(Object target,Object aspect,Method before,Method after){
        super();
        this.target = target;
        this.aspect = aspect;
        this.before =before;
        this.after = after;
    }


    /**
     *
     * @param clazz 接口的字节码
     * @param
     * @return
     */
    @Override
    public  Object newProxyInstance(Class clazz ) {
        System.out.println("---------------------ObjectProxy创建代理对象");
        // interfaceClazz 只能是接口
            //类加载器，
     Object objectProxy  =    Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[]{clazz},this);



        return objectProxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理say111111111111111111111113333");

        //todo 切面之前的方法，异常不影响切面
        Object obj = null;
        try {
            // //todo 切面之前的方法，异常有影响
            if(before!=null) {
              Object beforeResult =  before.invoke(aspect, args);
//              args.add(beforeResult)
            }
            obj = method.invoke(target, args);
            //todo 目标方法执行后，异常有影响
        }catch (RuntimeException e){
            System.out.println("代理say22222222222222222222222222异常");
            //todo 发生异常时执行的方法
        }
        //todo  //todo 目标方法执行后，异常不影响
        System.out.println("代理say22222222222222222222222222");
        return obj;
    }


}

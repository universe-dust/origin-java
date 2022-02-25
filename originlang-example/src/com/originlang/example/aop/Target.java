package com.originlang.example.aop;

public class Target  implements TargetInterface  {


    @Override
    public String toString() {
        return super.toString();
    }

    public String add(String str){
        return "成功"+str;
    }

    public static void main(String[] args) {
            TransactionInvocationHandler transactionInvocationHandler = new TransactionInvocationHandler(new Target());
        Target proxy = (Target) transactionInvocationHandler.getProxy();
          String res =  proxy.add("代理1");
        System.out.println(res);

//            proxy.i

    }
}

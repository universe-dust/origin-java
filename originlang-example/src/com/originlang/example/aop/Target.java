package com.originlang.example.aop;

public class Target  implements TargetInterface  {


    @Override
    public String toString() {
        return super.toString();
    }

    public String add(String str){
        return "ๆๅ"+str;
    }

    public static void main(String[] args) {
            TransactionInvocationHandler transactionInvocationHandler = new TransactionInvocationHandler(new Target());
        Target proxy = (Target) transactionInvocationHandler.getProxy();
          String res =  proxy.add("ไปฃ็1");
        System.out.println(res);

//            proxy.i

    }
}

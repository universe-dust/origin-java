package com.originlang.log;

public class LogFactory {
    public static JdkLogFacade getLogger(Class clazz){
        //todo 配置
       return JdkLogFacade.getInstance(clazz);
    }

}

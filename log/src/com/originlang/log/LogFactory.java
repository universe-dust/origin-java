package com.originlang.log;

public class LogFactory {
    public static JdkLogFacade getLogger(Class clazz){
       return JdkLogFacade.getInstance(clazz);
    }

}

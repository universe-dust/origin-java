package com.originlang.base.log;


import java.util.logging.Logger;

//设计单例-门面
public class JdkLogFacade implements LogFacade {
    private JdkLogFacade() {

    }

    private static final JdkLogFacade jdkLogFacade = new JdkLogFacade();

    //jdk日志对象
    private final Logger logger = Logger.getGlobal();

    public static JdkLogFacade getInstance(Class clazz) {
        return jdkLogFacade;
    }


    @Override
    public void debug(String message) {

        jdkLogFacade.logger.fine(message);
    }

    @Override
    public void info(String message) {

//        Logger.getLogger("com").info( message);
        System.out.println(message);
    }

    @Override
    public void warn(String message) {
        jdkLogFacade.logger.warning(message);
    }

    @Override
    public void error(String message) {
//        jdkLogFacade.logger.severe(message);
        System.out.println(message);
    }


}

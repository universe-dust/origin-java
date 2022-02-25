package com.originlang.domain.context;

import com.originlang.domain.context.annotation.Application;
import com.originlang.domain.context.bean.factory.ApplicationObjectFactory;
import com.originlang.domain.context.bean.factory.ObjectFactory;
import com.originlang.domain.context.ioc.ApplicationContext;
import com.originlang.domain.context.ioc.ApplicationDependencyInjection;
import com.originlang.domain.context.ioc.DependencyInjection;
import com.originlang.domain.context.scan.ApplicationScanner;
import com.originlang.domain.context.scan.Scanner;
import com.originlang.log.LogFacade;
import com.originlang.log.LogFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class ApplicationBootstrap {
    static LogFacade logFacade = LogFactory.getLogger(ApplicationBootstrap.class);

    public static void run(Class mainClazz, String[] args) {
        logFacade.info("JavaApplication starting >>>>>");
        doRun(mainClazz, args);
        logFacade.info("==============================");
        logFacade.info("JavaApplication started <<<<<");
        logFacade.info("==============================");
    }

    private static void doRun(Class mainClazz, String[] args) {

//        System.out.println(mainClazz.getPackageName());


        // 启动类注解判断
        Application application = (Application) mainClazz.getAnnotation(Application.class);
        String env =  application.env();
        logFacade.info("项目启动环境:" + env);
        ApplicationConfiguration.env = env;


        //加载配置类

        //包扫描，得到类名
        Scanner scanner = new ApplicationScanner();
       List<String> classNameList= scanner.scan(mainClazz);



       //生产对象，封装到beanDefinition
        ObjectFactory objectFactory =new ApplicationObjectFactory();
        try {
            //todo
            objectFactory.createObject(classNameList);
        } catch (ClassNotFoundException e) {
            //todo 异常处理
            e.printStackTrace();
        }


        //注入容器
        DependencyInjection dependencyInjection =new ApplicationDependencyInjection();
        dependencyInjection.dependencyInjection(classNameList);



        // 应用初始化




    }


}

package com.originlang.domain.context;

import com.originlang.domain.context.annotation.Application;
import com.originlang.domain.context.scan.ApplicationScanner;
import com.originlang.log.LogFacade;
import com.originlang.log.LogFactory;

public class ApplicationBootstrap {
    static LogFacade logFacade = LogFactory.getLogger(ApplicationBootstrap.class);

    public static void run(Class mainClazz, String[] args) {
        doRun(mainClazz, args);
    }

    private static void doRun(Class mainClazz, String[] args) {
        logFacade.info("JavaApplication starting >>>>>");
//        System.out.println(mainClazz.getPackageName());


        // 启动类注解判断
        Application application = (Application) mainClazz.getAnnotation(Application.class);
        String env =  application.env();
        logFacade.info("项目启动环境" + env);
        ApplicationConfiguration.env = env;


        //包扫描
        ApplicationScanner.scan(mainClazz);

        //加载配置类

        //加载类

        // 应用初始化

        logFacade.info("JavaApplication started <<<<<");
    }


}

package com.originlang.domain.context;

import com.originlang.domain.context.scan.ApplicationScanner;
import com.originlang.log.LogFacade;
import com.originlang.log.LogFactory;

public class JavaApplication {
 static    LogFacade logFacade = LogFactory.getLogger(JavaApplication.class);
   public static  void run(String[] args){
        logFacade.info("JavaApplication starting >>>>>");

       //包扫描
       ApplicationScanner.scan();

       //加载配置类

        //加载类

       // 应用初始化

       logFacade.info("JavaApplication started <<<<<");

    }


    public static void main(String[] args) {
        run(args);
    }






}

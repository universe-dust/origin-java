package com.originlang.domain.context.ioc;

import java.util.Collection;
import java.util.List;

//依赖注入类
public class ApplicationDependencyInjection implements DependencyInjection {



    public void dependencyInjection(List classNameList){
        doDependencyInjection(classNameList);
    }



    private void doDependencyInjection(List<String> classNameList){
        classNameList.stream().forEach(className-> {
            System.out.println(className);
            try {
                Class c =  Class.forName(className);

//                    Object o =    c.getDeclaredConstructor().newInstance();
//                 Object o2 =     Thread.currentThread().getContextClassLoader().loadClass(e).getDeclaredConstructor().newInstance();
                // 待优化，使用自己的类加载器，目前想到使用回调函数实现 todo
                Object obj =c.getClassLoader().loadClass(className).getDeclaredConstructor().newInstance();
                //放入容器
                ApplicationContext.registerSingleton(className,obj);

                System.out.println(obj);
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        });
    }
}

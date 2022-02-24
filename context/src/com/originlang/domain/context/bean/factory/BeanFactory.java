package com.originlang.domain.context.bean.factory;

//创建Bean接口
public interface BeanFactory {
    Object getBean(String beanName);
    Object getBean(Class<?> beanName);



}

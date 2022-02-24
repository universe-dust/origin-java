package com.originlang.domain.context.bean.definition;



// 实现的benn定义
public class ApplicationAnnotationBeanDefinition extends AnnotationBeanDefinition{

    // bean名称
//    String beanName;
    // 构造方法
    // 属性

    //其他方法


    @Override
    public void setBeanName(String beanClassName) {
        this.beanName = beanClassName;
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }
}

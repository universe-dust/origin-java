package com.originlang.domain.context.bean.definition;



// 实现的benn定义
public class ApplicationAnnotationBeanDefinition extends AnnotationBeanDefinition{



    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }
}

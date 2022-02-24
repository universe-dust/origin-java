package com.originlang.domain.context.annotation;

import java.lang.annotation.*;

//依赖注入
// 文档显示
@Documented
// 作用在类上
@Target({ElementType.TYPE,ElementType.FIELD,ElementType.ANNOTATION_TYPE})
//子类可以继承
@Inherited
// 可以反射获取
@Retention(RetentionPolicy.RUNTIME)
public @interface DependencyInjection {




}

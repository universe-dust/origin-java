package com.originlang.domain.context.annotation;

// 依赖注入，标记哪些类注入容器

import java.lang.annotation.*;

// 作用在类上
@Target({ElementType.TYPE,ElementType.FIELD})
//子类可以继承
@Inherited
// 可以反射获取
@Retention(RetentionPolicy.RUNTIME)
@DependencyInjection
public @interface Entity {


}

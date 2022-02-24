package com.originlang.domain.context.annotation;

import java.lang.annotation.*;

// 作用在类上
@Target(ElementType.TYPE)
//子类可以继承
@Inherited
// 可以反射获取
@Retention(RetentionPolicy.RUNTIME)
public @interface Domain {


}

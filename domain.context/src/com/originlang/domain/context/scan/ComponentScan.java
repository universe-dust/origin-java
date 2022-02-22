package com.originlang.domain.context.scan;
// 配置扫描

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
//@Repeatable(ComponentScans.class)
public @interface ComponentScan {


}

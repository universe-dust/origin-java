open module context {
    exports com.originlang.domain.context;
    exports com.originlang.domain.context.ioc;
    exports com.originlang.domain.context.aop;
    exports com.originlang.domain.context.bean.factory;
    exports com.originlang.domain.context.bean;
    exports com.originlang.domain.context.annotation;
    requires java.base;
    requires base;
    requires log;
}
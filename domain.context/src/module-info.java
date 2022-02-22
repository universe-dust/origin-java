module domain.context {
    exports com.originlang.domain.context;
    exports com.originlang.domain.context.ioc;
    exports com.originlang.domain.context.aop;
    exports com.originlang.domain.context.bean.factory;
    exports com.originlang.domain.context.bean;
    requires java.base;
    requires base;
    requires log;
}
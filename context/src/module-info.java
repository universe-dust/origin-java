open module context {
    exports com.originlang.domain.context;
    exports com.originlang.domain.context.ioc;
    exports com.originlang.domain.aop;
    exports com.originlang.domain.context.ioc.object.factory;

    exports com.originlang.domain.context.annotation;
    requires java.base;
    requires base;
    requires log;
}
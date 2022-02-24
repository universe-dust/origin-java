package com.originlang.domain.context.bean.factory;
@FunctionalInterface
public interface ObjectFactory<T> {
    T getObject();

}

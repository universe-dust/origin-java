package com.originlang.domain.context.ioc.object.factory;

import java.util.List;

public interface ObjectFactory {


    void createObject(List<String> classNameList) throws ClassNotFoundException;
}
package com.originlang.context.ioc.object.factory;

import com.originlang.context.ioc.object.definition.ObjectDefinition;

import java.util.List;
import java.util.Map;

public interface ObjectFactory {


    Map<String, ObjectDefinition> createObject(List<String> classNameList, Map<String, ObjectDefinition> collection) throws ClassNotFoundException;



}

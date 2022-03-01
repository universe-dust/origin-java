package com.originlang.domain.context.ioc.object.definition;

public class ObjectDefinitionBuilder {



    public ObjectDefinitionBuilder Builder(String clazzName){


        return this;
    }
    public ObjectDefinition Build(){


        return new ApplicationAnnotationObjectDefinition();
    }


    public static void main(String[] args) {
        ObjectDefinitionBuilder builder = new ObjectDefinitionBuilder();
        ObjectDefinition objectDefinition = builder.Builder("").Build();
    }


}

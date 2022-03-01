package com.originlang.base.log;

public interface LogFacade {

    /*
      default:
      1. detault关键字修饰的方法称为默认方法
      2. 子类可以选择性的是否重写该方法
      3. 接口中的default方法通过实例对象来调用
  static:
      1. static关键字修饰的方法称为静态方法
      2. 子类不能重写该方法
      3. 接口中的静态方法通过接口名.方法名()的方式来

     */

     void debug(String message) ;

     void info(String message) ;

     void warn(String message) ;

     void error(String message) ;

}

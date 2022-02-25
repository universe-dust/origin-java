package com.originlang.domain.context.scan;

import java.util.Collection;
import java.util.List;

public interface Scanner {
      //扫描主类所在包和子包，子类必须实现
       List<String> scan(Class mainClazz) ;
      //扫描自定义包，选择实现
      default Collection<String> scan(Collection<String> scanPkg) {
          return scanPkg;
      }
}

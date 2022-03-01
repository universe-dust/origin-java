

# 目标
- 一个只依赖JDK和其他驱动的项目。
- 方便项目DDD的实现
- 注释详细， 推荐全部使用单行注释，防止不同的编辑器缩进带来阅读问题
# 项目说明
- 使用时必须定义为 open model
- 基于JDK17 
- 可以反射的Open Model : core
- object对应spring框架中的bean
## AOP
基于jdk动态代理实现，被代理对象必须有一个父接口，容器中注入的是代理对象；
获取代理对象的方式为get(父类接口全名)
## 对比Spring对象创建
- 扫描
- 包装成ObjectDefinition，放入三级缓存
- 依赖注入，先放入二级，
- 
spring的依赖注入是：
- 创建bean，map<name,beanFactory>,
- 放入三级缓存
- 注入属性，解决循环依赖，从三级缓存取出，移入二级
- bean初始化，生成aop代理类




# 项目结构
scanner 扫描
objectFactory-生产objectDefinition
context--注入内部ioc容器中；

> core  -基础模块  
>  
> log 日志
> 
> server - web服务器

# 命名规范
> 模块命名
> > 全部小写，多个单词xxxyyy的格式    
> > 
> 
> 包命名
> > 全部小写  
> > com.originlang.模块名    
> 
> 类命名 
> > 首字母大写，驼峰命名；
> > 名称尽量完整 ，不要担心名称过长；
> > 用到设计模式带上设计模式名称
> 
> 变量命名
> >首字母大写，驼峰命名；     
> > 名称尽量完整 ，不要担心名称过长；
> > 如果是集合类数据，带上类型，如
> >> xxxArray , xxxList,xxxSet
> 
> 方法命名
> > 
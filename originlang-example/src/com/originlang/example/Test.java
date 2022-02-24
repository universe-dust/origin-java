//package com.originlang.example;
//
//
//
//
//
//
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.XPath;
//import org.dom4j.io.SAXReader;
//public class ClassPathXMLApplicationContext {
//    //用来存放bean的信息
//    private List<BeanDefinition> BeanDefines = new ArrayList<BeanDefinition>();
//    //保存bean
//    private Map<String ,Object > sigletons =new HashMap<String, Object>();
//
//    public  ClassPathXMLApplicationContext(String fileName){
//        this.readXML(fileName);//读XML文件
//        this.instanceBeans();//实例化bean对象
//        this.annotationInject();//处理注解的方法
//        this.injectObject();//为bean对象的属性注入值
//    }
//
//    //处理注解的方法
//    private void annotationInject() {
//        // 遍历所有的bean对象
//        for(String beanName:sigletons.keySet()){
//            Object  bean = sigletons.get(beanName);
//            if(bean!=null ){
//                try {
//                    PropertyDescriptor[] ps = Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
//                    for(PropertyDescriptor properdesc:ps){
//                        Method setter = properdesc.getWriteMethod();
//                        if(setter!=null && setter.isAnnotationPresent(AllenResource.class)){
//                            AllenResource resource = setter.getAnnotation(AllenResource.class);
//                            Object value = null;
//                            if(resource.name()!=null && !"".equals(resource.name())){
//                                value = sigletons.get(resource.name());
//                            }else {
//                                value = sigletons.get(properdesc.getName());
//                                if(value==null){
//                                    for(String key : sigletons.keySet()){
//                                        if(properdesc.getPropertyType().isAssignableFrom(sigletons.get(key).getClass())){
//                                            value = sigletons.get(key);
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                            setter.setAccessible(true);
//                            setter.invoke(bean, value);
//                        }
//                    }
//
//                    Field[] fields = bean.getClass().getDeclaredFields();
//                    for(Field field:fields){
//                        if(field.isAnnotationPresent(AllenResource.class)){
//                            AllenResource resource = field.getAnnotation(AllenResource.class);
//                            Object value = null;
//                            if(resource.name()!=null && !"".equals(resource.name())){
//                                value = sigletons.get(resource.name());
//                            }else {
//                                value = sigletons.get(field.getName());
//                                if(value==null){
//                                    for(String key : sigletons.keySet()){
//                                        if(field.getType().isAssignableFrom(sigletons.get(key).getClass())){
//                                            value = sigletons.get(key);
//                                            break;
//                                        }
//                                    }
//                                }
//                            }
//                            field.setAccessible(true);
//                            field.set(bean, value);
//                        }
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    //为bean对象的属性注入值
//    private void injectObject() {
//        for (BeanDefinition beanDefinition :BeanDefines) {
//            //实例化过后的bean
//            Object bean = sigletons.get(beanDefinition.getId());
//            if(bean!=null){
//                try {
//                    PropertyDescriptor[] ps=Introspector.getBeanInfo(bean.getClass()).getPropertyDescriptors();
//                    for(PropertyDefinition propertyDefinition:beanDefinition.getProperties()){
//                        for(PropertyDescriptor properdesc:ps){
//                            if(propertyDefinition.getName().equals(properdesc.getName())){
//                                Method setter =properdesc.getWriteMethod();//获取setter方法
//                                if(setter!=null){
//                                    Object value =null;
//                                    if(propertyDefinition.getRef()!=null && !"".equals(propertyDefinition.getRef())){
//                                        value  =sigletons.get(propertyDefinition.getRef());
//                                        System.out.println(value);
//                                    }else {
//                                        System.out.println(properdesc.getPropertyType());
//                                        value  =propertyDefinition.getValue();
//                                    }
//                                    setter.setAccessible(true);//允许调用private的方法
//                                    setter.invoke(bean, value);//把应用对象注入到属性
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//    //完成bean的实例化
//    private void instanceBeans() {
//        for(BeanDefinition beanDefinition:BeanDefines){
//            try {
//                if(beanDefinition.getClassName()!=null && !"".equals(beanDefinition.getClassName().trim()))
//                    sigletons.put(beanDefinition.getId(),Class.forName(beanDefinition.getClassName()).newInstance());
//            } catch (Exception e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //读取app*.xml里面的内容
//    private void readXML(String fileName) {
//        SAXReader  saxReader = new SAXReader();
//        Document  document =null;
//        try{
//            URL xmlPath=this.getClass().getClassLoader().getResource(fileName);
//            //System.out.println(xmlPath);file:/G:/workspaces1/spring/bin/beans.xml
//            document =saxReader.read(xmlPath);
//            Map<String,String> nsMap=new HashMap<String, String>();
//            //加入命名空间
//            nsMap.put("ns","http://www.springframework.org/schema/beans");
//            //创建beans/bean的查询路径
//            XPath xsub = document.createXPath("//ns:beans/ns:bean");
//            //System.out.println(xsub);[XPath: /descendant-or-self::node()/child::ns:beans/child::ns:bean]
//            //设置命名空间
//            xsub.setNamespaceURIs(nsMap);
//            //获取文档下的所有的bean节点
//            List<Element>  beans = xsub.selectNodes(document);
//            for (Element element : beans) {
//                String id =element.attributeValue("id");//获取id属性值
//                String clazz = element.attributeValue("class");//获取class属性值
//                BeanDefinition beanDefine =new  BeanDefinition(id, clazz);
//
//                //得到bean下面的所有property节点
//                XPath propertyPath = document.createXPath("ns:property");
//                propertyPath.setNamespaceURIs(nsMap);
//                List<Element> properties =propertyPath.selectNodes(element);
//
//                for(Element property:properties){
//                    String  propertyName = property.attributeValue("name");
//                    String  propertyRef  = property.attributeValue("ref");
//                    String  propertyValue  = property.attributeValue("value");
//                    //System.out.println(propertyName+"="+propertyRef+propertyValue);
//                    PropertyDefinition propertyDefinition =new  PropertyDefinition(propertyName, propertyRef,propertyValue);
//                    beanDefine.getProperties().add(propertyDefinition);
//                }
//
//                BeanDefines.add(beanDefine);
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }
//    //获取bean实例
//    public Object getBean(String beanName){
//        return this.sigletons.get(beanName);
//    }
//}
//
//
//
//
//
//
////--------------------------------
//
//public class Test {
//
//
//
//
//
//
//
//}
//
//
//

package com.originlang.domain.context.scan;


import com.originlang.base.SystemInfo;
import com.originlang.domain.context.ApplicationBootstrap;
import com.originlang.domain.context.ioc.ApplicationContext;
import com.originlang.log.LogFacade;
import com.originlang.log.LogFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


// 扫描类 + 配置文件
public class ApplicationScanner implements Scanner{
   private static LogFacade logFacade = LogFactory.getLogger(ApplicationBootstrap.class);

    List<String> classNameList = new ArrayList<>(512);
    /**
     *
     * @param mainClazz  类字节码
     */
    @Override
    public  List<String> scan(Class mainClazz) {
//       String userDir = SystemInfo.userDir;

        try {
//        处理后多一个 /
            String mainClazzPath = URLDecoder.decode(mainClazz.getResource("").getPath(), "UTF-8");
//            System.out.println(mainClazz.getClassLoader().getResources("/").nextElement().getFile());
            logFacade.info(">>>>>start class in mainClazzPath="+mainClazzPath);


//            File mainFileDir = new File(mainClazzPath);
//            System.out.println("11111111111111111111");
//            File[] dirfiles = mainFileDir.listFiles(new FileFilter() {
//                // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
//                @Override
//                public boolean accept(File file) {
//                    System.out.println("是否class文件+" + file.isDirectory() + file.getName().endsWith(".class"));
//                    return   (file.isDirectory()) || (file.getName().endsWith(".class"));
//                }
//            });

                doScan(mainClazzPath,mainClazz.getPackageName());





//            doScan(mainClazz,mainClazzPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ///C:/Users/snow/Desktop/GitRepository/originlang-java/out/production/originlang-example/com/
//        logFacade.info(">>>>> classload"+mainClazz.getClassLoader().getResource("com"));

        return classNameList;
    }

    private  void  doScan(String dirPath,String pkg){
        logFacade.debug("路径="+dirPath + "包="+pkg);
        File mainFileDir = new File(dirPath);
        File[] dirfiles = mainFileDir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                System.out.println("是否class文件+" + file.isDirectory() + file.getName().endsWith(".class"));
                return   (file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });

        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                doScan(dirPath + SystemInfo.fileSeparator+file.getName(),pkg + "."+file.getName() );
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名

                String className = file.getName().substring(0, file.getName().length() - 6);
                System.out.println("className000000"+className);
                // 添加到集合中去
                // classes.add(Class.forName(packageName + '.' +
                // className));
                // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
//                    System.out.println(clazz.getClassLoader().loadClass(packageName + '.' + className).getName());
//                    classes.add(clazz.getClassLoader().loadClass(packageName + '.' + className));
                classNameList.add(pkg + "." +className);
            }
        }
    }


//    public static void scan(Class clazz,String... pkgPath) {
//        if (pkgPath == null || pkgPath.length == 0) {
//            doScan(clazz);
//            return;
//        }
//        doScan(clazz,pkgPath);
//    }


    private static void doScan(Class mainClazz,String... clazzPathArray) {
        Set<Class<?>> appClass = new LinkedHashSet<Class<?>>();

        Arrays.stream(clazzPathArray).forEach(
                clazzPath->{
                    logFacade.info("JavaApplication component scan  dir = " + clazzPath);
                    Set<Class<?>> pkgClasses =    getPkgClasses(mainClazz,clazzPath);
                    appClass.addAll(pkgClasses);
                    pkgClasses.stream().forEach(clazz2-> System.out.println("---scanned clazz" + clazz2.getName()));

                }
        );



    }

    /**
     * 根据包名获取包下面所有的类名
     *
     */
    private static   Set<Class<?>> getPkgClasses(Class mainClazz,String clazzPath)  {
        logFacade.info("根据包名获取包下面所有的类名:"+clazzPath);
        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageName = mainClazz.getPackageName();
//        String packageDirName = packageName.replace('.', SystemInfo.fileSeparatorChar);
//       String packageDirName=;
        System.out.println("packageName="+packageName+"---------------------------clazzPath="+ clazzPath);
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(clazzPath);







            System.out.println("hasmore=" + dirs.hasMoreElements());

            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                System.out.println("--------------------------------protocol="+protocol);
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.out.println("========================================file");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    System.out.println("filePath="+ filePath);
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    getClazzInPackage(mainClazz,packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.out.println("**************************************jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        findClassesInPackageByJar(packageName, entries, clazzPath, recursive, classes);
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }



    /**
     * 以文件的形式来获取包下的所有Class
     *
     */
    private static void getClazzInPackage(Class clazz,String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            System.out.println("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            @Override
            public boolean accept(File file) {
                System.out.println("是否class文件+" + file.isDirectory() + file.getName().endsWith(".class"));
                return recursive && (file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        // 循环所有文件
        for (File file : dirfiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                getClazzInPackage(clazz,packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                System.out.println("className000000"+className);
                try {
                    // 添加到集合中去
                    // classes.add(Class.forName(packageName + '.' +
                    // className));
                    // 经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    System.out.println(clazz.getClassLoader().loadClass(packageName + '.' + className).getName());
                    classes.add(clazz.getClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    logFacade.error("ClassNotFoundException="+e.getMessage());
                }
            }
        }
    }

    /**
     * 以jar的形式来获取包下的所有Class
     *
     * @param packageName
     * @param entries
     * @param packageDirName
     * @param recursive
     * @param classes
     */
    private static void findClassesInPackageByJar(String packageName, Enumeration<JarEntry> entries, String packageDirName, final boolean recursive, Set<Class<?>> classes) {
        // 同样的进行循环迭代
        while (entries.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
            JarEntry entry = entries.nextElement();
            String name = entry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }
            // 如果前半部分和定义的包名相同
            if (name.startsWith(packageDirName)) {
                int idx = name.lastIndexOf('/');
                // 如果以"/"结尾 是一个包
                if (idx != -1) {
                    // 获取包名 把"/"替换成"."
                    packageName = name.substring(0, idx).replace('/', '.');
                }
                // 如果可以迭代下去 并且是一个包
                if ((idx != -1) || recursive) {
                    // 如果是一个.class文件 而且不是目录
                    if (name.endsWith(".class") && !entry.isDirectory()) {
                        // 去掉后面的".class" 获取真正的类名
                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                        try {
                            // 添加到classes
                            classes.add(Class.forName(packageName + '.' + className));
                        } catch (ClassNotFoundException e) {
                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


}

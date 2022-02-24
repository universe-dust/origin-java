package com.originlang.example;



import com.originlang.base.SystemInfo;

import java.io.File;
import java.lang.reflect.Method;

/**
 * 可以打印出所有的包名及类名
 */
public class ClassUtil {

    public static String getPackageName(String fqClassName) {

        int lastDotIndex = fqClassName.lastIndexOf(".");
        return (lastDotIndex != -1 ? fqClassName.substring(0, lastDotIndex) : "");
    }

    public static void main(String[] args) throws Exception {
        String packageName = "com.originlang";
        System.out.println(ClassUtil.class.getResource("").getPath());
        // 获取当前项目地址
        File root = new File(System.getProperty("user.dir") + "\\");
        loop(root, packageName);
    }

    public static void loop(File folder, String packageName) throws Exception {
        // 加载当前项目所有文件
        File[] files = folder.listFiles();
        for (int fileIndex = 0; fileIndex < files.length; fileIndex++) {
            File file = files[fileIndex];
            if (file.isDirectory()) {
                loop(file, packageName + file.getName() + ".");
            } else {
                listMethodNames(file.getName(), packageName);
            }
        }
    }

    public static void listMethodNames(String filename, String packageName) {
        try {
            String name = filename.substring(0, filename.length() - 5);
            Object obj = Class.forName(packageName + name);
            Method[] methods = obj.getClass().getDeclaredMethods();
            System.out.println(filename);
            for (int i = 0; i < methods.length; i++) {
                System.out.println("\t" + methods[i].getName());
            }
        } catch (Exception e) {
            System.out.println("exception = " + e.getLocalizedMessage());
        }
    }
}
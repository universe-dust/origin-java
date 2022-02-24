package com.originlang.base;

import java.io.File;

public final class SystemInfo {

    //当前工程所在文件夹
    public static  String userDir = System.getProperty("user.dir");

    public  static String lineSeparator = System.lineSeparator();

    //文件分隔符
    public static String fileSeparator= File.separator;
    public static char fileSeparatorChar= File.separatorChar;

//    public static void main(String[] args) {
//        System.out.println("2");
//        System.out.println("user"+userDir);
//        System.out.println(lineSeparator);
//        System.out.println(fileSeparator);
//
//    }

    static private int a = 10;
    static int b = 20;
    static protected int c = 30;
    static public int d = 40;


}

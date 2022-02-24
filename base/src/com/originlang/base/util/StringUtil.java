package com.originlang.base.util;

public class StringUtil {


    public static boolean isEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isBlank(String str) {
        if (str == null || str.isBlank()) {
            return true;
        }
        return false;
    }

}

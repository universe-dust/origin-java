package com.originlang;


import com.originlang.util.StringUtil;
import com.originlang.server.ServerStart;

public class Main {

    public static void main(String[] args) {
       boolean res = StringUtil.isEmpty(" ");
       boolean res2 = StringUtil.isBlank(" ");
        System.out.println(res2);

        ServerStart.run(Main.class,args);
    }
}

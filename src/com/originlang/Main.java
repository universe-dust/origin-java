package com.originlang;


import com.originlang.server.Server;
import com.originlang.util.StringUtil;

public class Main {

    public static void main(String[] args) {
       boolean res = StringUtil.isEmpty(" ");
       boolean res2 = StringUtil.isBlank(" ");
        System.out.println(res2);

        Server.run(Main.class,args);
    }
}

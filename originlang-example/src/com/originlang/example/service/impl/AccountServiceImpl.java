package com.originlang.example.service.impl;

import com.originlang.domain.aop.PointCut;
import com.originlang.example.service.AccountService;

public class AccountServiceImpl implements AccountService {


    @Override
    public void login() {
        System.out.println("account login");
    }
}

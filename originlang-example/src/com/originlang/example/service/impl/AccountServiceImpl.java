package com.originlang.example.service.impl;

import com.originlang.domain.context.annotation.Dependency;
import com.originlang.example.service.AccountService;

@Dependency
public class AccountServiceImpl implements AccountService {


    @Override
    public void login() {
        System.out.println("account login");
    }
}

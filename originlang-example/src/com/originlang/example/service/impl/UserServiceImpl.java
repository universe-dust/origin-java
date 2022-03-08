package com.originlang.example.service.impl;

import com.originlang.context.annotation.Dependency;
import com.originlang.example.service.AccountService;
import com.originlang.example.service.UserService;
@Dependency
public class UserServiceImpl implements UserService {

    @Dependency
    AccountService accountService;

    @Override
    public void userLogin() {
        System.out.println("user login**********");
        accountService.login();
    }
}

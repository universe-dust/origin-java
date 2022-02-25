package com.originlang.example.service.impl;

import com.originlang.domain.context.annotation.DependencyInjection;
import com.originlang.example.service.AccountService;
import com.originlang.example.service.UserService;

public class UserServiceImpl implements UserService {

    @DependencyInjection
    AccountService accountService;

    @Override
    public void userLogin() {
        System.out.println("user login**********");
        accountService.login();
    }
}

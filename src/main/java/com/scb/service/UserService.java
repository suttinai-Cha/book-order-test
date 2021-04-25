package com.scb.service;

import com.scb.model.Account;

import java.util.List;

public interface UserService {
    public Long addUUser(Account user);

    public Account getUserInfo(String userName);

    public void removeUser(String userName);

    public Double bookOrder(String userName ,List<Long> orders);
}

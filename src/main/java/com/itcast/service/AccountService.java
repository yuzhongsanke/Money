package com.itcast.service;

import com.itcast.domain.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAllAccounts();

    void saveAccount(Account account);

    Account findAccountByAid(Integer aid);

    void updateAccount(Account account);

    void deleteAccounts(Integer[] aid);
}

package com.itcast.dao;

import com.itcast.domain.Account;

import java.util.List;

public interface AccountDao {
    List<Account> findAllAccounts();

    void saveAccount(Account account);

    Account findAccountByAid(Integer aid);

    void updateAccount(Account account);

    void deleteAccountByAid(Integer aid);
}

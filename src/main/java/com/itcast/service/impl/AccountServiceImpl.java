package com.itcast.service.impl;

import com.itcast.dao.AccountDao;
import com.itcast.domain.Account;
import com.itcast.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AccountServiceImpl implements AccountService {

@Autowired
    private AccountDao accountDao;


    public List<Account> findAllAccounts() {
        List<Account> aalllll = accountDao.findAllAccounts();
        return aalllll;
    }

    public void saveAccount(Account account) {
        accountDao.saveAccount(account);

    }

    public Account findAccountByAid(Integer aid) {
        return accountDao.findAccountByAid(aid);
    }

    public void updateAccount(Account account) {
        accountDao.updateAccount(account);

    }

    public void deleteAccounts(Integer[] aids) {
        for (Integer aid:aids){
            accountDao.deleteAccountByAid(aid);
        }

    }
}

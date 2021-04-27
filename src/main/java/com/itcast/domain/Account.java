package com.itcast.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {



    private static final long serialVersionUID = 1L;

    private Integer aid;//主键

    private String number;//账号

    private BigDecimal balance;//余额


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

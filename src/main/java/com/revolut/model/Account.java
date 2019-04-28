package com.revolut.model;

import java.math.BigDecimal;

public class Account {
    private Long accountNumber;
    private String accountHolderName;
    private String bankName;
    private String bankBranch;
    private String accountCurrencyType;
    private BigDecimal balance;
    private boolean active;

    public Account() {
    }

    public Account(long accountNumber, BigDecimal balance) {
        this.accountCurrencyType = accountHolderName;
        this.balance = balance;
    }

    public synchronized boolean withdraw(BigDecimal amount) {

        if (this.balance.compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    public synchronized boolean deposit(BigDecimal amount) {
        balance = balance.add(amount);
        return true;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountCurrencyType() {
        return accountCurrencyType;
    }

    public void setAccountCurrencyType(String accountCurrencyType) {
        this.accountCurrencyType = accountCurrencyType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

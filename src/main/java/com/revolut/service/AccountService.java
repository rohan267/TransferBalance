package com.revolut.service;

import com.revolut.model.Account;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    List<Account> accounts = new ArrayList<>();

    AccountService() {
        Account account1 = new Account();
        account1.setAccountNumber(1L);
        account1.setBalance(new BigDecimal(10_000));
        accounts.add(account1);

        Account account2 = new Account();
        account2.setAccountNumber(2L);
        account2.setBalance(new BigDecimal(20_000));
        accounts.add(account2);
    }

    public Account getAccount(long fromAccountId) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == fromAccountId)
                return account;
        }
        return null;
    }
}

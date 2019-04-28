package com.revolut.service;

import com.revolut.ValidationUtil;
import com.revolut.model.Account;
import com.revolut.model.TransferResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TransactionService {

    @Autowired
    AccountService accountService;

    public TransferResult executeTransfer(long fromAccountId, long toAccountId, BigDecimal transferAmount) {
        TransferResult transferResult = new TransferResult();

        Account fromAccount = accountService.getAccount(fromAccountId);
        Account toAccount = accountService.getAccount(toAccountId);

        if (!ValidationUtil.isValidTransfer(fromAccount, toAccount, transferAmount, transferResult)) {
            return transferResult;
        }
        fromAccount.withdraw(transferAmount);
        toAccount.deposit(transferAmount);

        transferResult.addReason("Transfer success");
        transferResult.setSuccess(true);

        return transferResult;

    }
}

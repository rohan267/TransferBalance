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

    public TransferResult executeTransfer(Account fromAccount, Account toAccount, BigDecimal transferAmount) {
        TransferResult transferResult = new TransferResult();

        if (!ValidationUtil.isValidTransfer(fromAccount, toAccount, transferAmount, transferResult)) {
            return transferResult;
        }

        fromAccount.withdraw(transferAmount);
        toAccount.deposit(transferAmount);

        transferResult.setReason("Transfer success");
        transferResult.setSuccess(true);

        return transferResult;
    }
}

package com.revolut;

import com.revolut.model.Account;
import com.revolut.model.TransferResult;

import java.math.BigDecimal;

public class ValidationUtil {

    public static boolean isValidTransfer(Account fromAccount, Account toAccount, BigDecimal transferAmount, TransferResult transferResult) {
        if (fromAccount.getBalance().compareTo(BigDecimal.ZERO) > 0 &&
                fromAccount.getBalance().compareTo(transferAmount) <= 0) {
            transferResult.setSuccess(false);
            transferResult.setReason("Insufficient balance to transfer");
            return false;
        }

        return true;
    }
}

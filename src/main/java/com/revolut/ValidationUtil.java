package com.revolut;

import com.revolut.model.Account;
import com.revolut.model.TransferResult;

import java.math.BigDecimal;

public class ValidationUtil {

    public static boolean isValidTransfer(Account fromAccount, Account toAccount, BigDecimal transferAmount, TransferResult transferResult) {
        if (!isTransferAmountValid(transferAmount, transferResult)
                || !isSufficientBalance(transferAmount, fromAccount.getBalance(), transferResult)
                || areAccountNumbersSame(fromAccount.getAccountNumber(), toAccount.getAccountNumber(), transferResult)) {
            transferResult.setSuccess(false);
            return false;
        }
        return true;
    }

    private static boolean areAccountNumbersSame(long fromAccountId, long toAccountId, TransferResult transferResult) {
        if (fromAccountId == toAccountId) {
            transferResult.addReason("Account numbers cannot be same");
            return true;
        }

        return false;
    }

    private static boolean isTransferAmountValid(BigDecimal transferAmount, TransferResult transferResult) {
        if (transferAmount.compareTo(BigDecimal.ZERO) <= 0) {
            transferResult.addReason("transfer amount cannot be less than or equals to zero");
            return false;
        }
        return true;
    }

    private static boolean isSufficientBalance(BigDecimal transferAmount, BigDecimal balance, TransferResult transferResult) {
        if (balance.compareTo(transferAmount) <= 0) {
            transferResult.addReason("Insufficient balance to transfer");
            return false;
        }
        return true;
    }
}

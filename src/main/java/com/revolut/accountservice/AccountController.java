package com.revolut.accountservice;

import com.revolut.model.TransferResult;
import com.revolut.service.AccountService;
import com.revolut.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(path = "/account")
public class AccountController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountService accountService;

    @GetMapping(path = "/index")
    public String index() {
        return "Revolut Account Service is up and running";
    }

    @GetMapping(path = "/checkbalance")
    public BigDecimal showBalance(@RequestParam(name = "accountNumber") long accountNum) {
        return accountService.getAccount(accountNum).getBalance();
    }

    /**
     * Accounts are hard coded in @AccountService class
     * Account 1 balance is 10,000
     * Account 2 balance is 20,000
     *
     * @param fromAccId
     * @param toAccId
     * @param transferAmount
     * @return
     */
    @GetMapping(path = "/transfer")
    public TransferResult transferAmount(@RequestParam(name = "fromAccount") long fromAccId
            , @RequestParam(name = "toAccount") long toAccId
            , @RequestParam(name = "transferAmount") BigDecimal transferAmount) {

        return transactionService.executeTransfer(fromAccId, toAccId, transferAmount);
    }
}

package com.revolut.service;

import com.revolut.accountservice.AccountserviceApplication;
import com.revolut.model.Account;
import com.revolut.model.TransferResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountserviceApplication.class)
public class TransactionServiceTest {

    private Map<Long, Account> accounts = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        Account account1 = new Account();
        account1.setBalance(new BigDecimal(10_000.00));

        Account account2 = new Account();
        account2.setBalance(new BigDecimal(20_000.00));

        accounts.put(1L, account1);
        accounts.put(2L, account2);
    }

    @Autowired
    private TransactionService transactionService;

    @Test
    public void testTransferAmountSuccess() {

        TransferResult result = transactionService.executeTransfer(accounts.get(1L),
                accounts.get(2L),
                new BigDecimal(2000.00));

        assertEquals(accounts.get(1L).getBalance(), new BigDecimal(8000.00));
        assertEquals(accounts.get(2L).getBalance(), new BigDecimal(22_000.00));
        assertEquals(result.isSuccess(), true);
    }

    @Test
    public void testTransferAmountSuccessReverse() {

        TransferResult result = transactionService.executeTransfer(accounts.get(2L),
                accounts.get(1L),
                new BigDecimal(2000.00));

        assertEquals(accounts.get(1L).getBalance(), new BigDecimal(12_000.00));
        assertEquals(accounts.get(2L).getBalance(), new BigDecimal(18_000.00));
        assertEquals(result.isSuccess(), true);
    }

    @Test
    public void testTransferAmountNoBanalnce() {
        TransferResult result = transactionService.executeTransfer(accounts.get(1L),
                accounts.get(2L),
                new BigDecimal(11_000));

        assertEquals(accounts.get(1L).getBalance(), new BigDecimal(10_000.00));
        assertEquals(accounts.get(2L).getBalance(), new BigDecimal(20_000.00));
        assertEquals(result.isSuccess(), false);
    }
}
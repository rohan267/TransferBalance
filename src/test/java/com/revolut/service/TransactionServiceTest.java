package com.revolut.service;

import com.revolut.accountservice.AccountserviceApplication;
import com.revolut.model.TransferResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountserviceApplication.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        accountService.initiateAccounts();
    }

    @Test
    public void testTransferAmountSuccess() {

        TransferResult result = transactionService.executeTransfer(1L, 2L,
                new BigDecimal(2000.00));

        assertEquals(new BigDecimal(8000.00), accountService.getAccount(1L).getBalance());
        assertEquals(new BigDecimal(22_000.00), accountService.getAccount(2L).getBalance());
        assertEquals(result.isSuccess(), true);
    }


    @Test
    public void testTransferAmountNoBanalnce() {
        TransferResult result = transactionService.executeTransfer(1L, 2L,
                new BigDecimal(11_000));

        assertEquals(new BigDecimal(10_000.00), accountService.getAccount(1L).getBalance());
        assertEquals(new BigDecimal(20_000.00), accountService.getAccount(2L).getBalance());
        assertEquals(result.isSuccess(), false);
    }

    @Test
    public void testNegativeTransferAmount() {
        TransferResult result = transactionService.executeTransfer(1L, 2L,
                new BigDecimal(-100.00));

        assertEquals(new BigDecimal(10_000.00), accountService.getAccount(1L).getBalance());
        assertEquals(new BigDecimal(20_000.00), accountService.getAccount(2L).getBalance());
        assertEquals(result.isSuccess(), false);
    }
}
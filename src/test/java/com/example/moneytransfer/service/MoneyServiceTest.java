package com.example.moneytransfer.service;

import com.example.moneytransfer.repository.BalanceRepository;
import com.example.moneytransfer.service.impl.MoneyServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class MoneyServiceTest {

    private BalanceRepository balanceRepository = new BalanceRepository();
    private MoneyServiceImpl moneyService = new MoneyServiceImpl(balanceRepository);

    @Test
    void getBalance() {

        final BigDecimal balance = moneyService.getBalance(1L);
        assertEquals(balance, BigDecimal.valueOf(1000));
    }

    @Test
    void addMoney() {

        final BigDecimal balance = moneyService.addMoney(1L, BigDecimal.valueOf(1000));
        assertEquals(balance, BigDecimal.valueOf(2000));
    }
}
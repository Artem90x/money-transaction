package com.example.moneytransfer.service;

import com.example.moneytransfer.model.TransferBalance;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface MoneyService {

    BigDecimal getBalance(Long accountId);

    BigDecimal addMoney(Long to, BigDecimal amount);

    void makeTransfer(TransferBalance transferBalance);
}

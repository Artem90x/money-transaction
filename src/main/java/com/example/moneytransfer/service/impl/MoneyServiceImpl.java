package com.example.moneytransfer.service.impl;


import com.example.moneytransfer.model.TransferBalance;
import com.example.moneytransfer.repository.BalanceRepository;
import com.example.moneytransfer.service.MoneyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class MoneyServiceImpl implements MoneyService {

    private final BalanceRepository repository;

    @Override
    public BigDecimal getBalance(Long accountId) {

        BigDecimal balance = repository.getBalanceForId(accountId);

        if (balance == null) {
            throw new IllegalArgumentException("no id");
        }
        return balance;
    }

    @Override
    public BigDecimal addMoney(Long to, BigDecimal amount) {

        BigDecimal currentBalance = repository.getBalanceForId(to);

        if (currentBalance == null) {
            repository.save(to, amount);
            return amount;
        } else {
            BigDecimal updateBalance = currentBalance.add(amount);
            repository.save(to, updateBalance);
            return updateBalance;
        }
    }

    @Override
    public void makeTransfer(TransferBalance transferBalance) {

        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getTo());

        if (fromBalance == null || toBalance == null)
            throw new IllegalArgumentException("no id");
        if (transferBalance.getAmount().compareTo(fromBalance) > 0)
            throw new IllegalArgumentException("no money");

        BigDecimal updateFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updateToBalance = toBalance.add(transferBalance.getAmount());

        repository.save(transferBalance.getFrom(), updateFromBalance);
        repository.save(transferBalance.getTo(), updateToBalance);
    }
}

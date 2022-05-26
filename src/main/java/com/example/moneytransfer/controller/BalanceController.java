package com.example.moneytransfer.controller;

import com.example.moneytransfer.model.TransferBalance;
import com.example.moneytransfer.service.MoneyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final MoneyService moneyService;

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
       return moneyService.getBalance(accountId);

    }

    @PostMapping("/{accountId}")
    public BigDecimal addMoney (@RequestBody TransferBalance transferBalance) {
        return moneyService.addMoney(transferBalance.getTo(), transferBalance.getAmount());

    }

    @PostMapping("/transfer")
    public void transfer (@RequestBody TransferBalance transferBalance) {
        moneyService.makeTransfer(transferBalance);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "Error =/";
    }
}

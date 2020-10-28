package com.github.zhalabkevich.transaction_manager.controller;

import com.github.zhalabkevich.transaction_manager.service.ServiceException;
import com.github.zhalabkevich.transaction_manager.service.ServiceFactory;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionController {
    private final ServiceFactory service = ServiceFactory.getInstance();

    public int getTotal(String merchant, LocalDateTime start, LocalDateTime end) {
        int total = 0;
        try {
            total = service.getTransactionService().getTotalNumberOfTransactions(merchant, start, end);
        } catch (ServiceException e) {
            System.out.println("Can't get data from server. Please, try later!");
        }
        return total;

    }

    public BigDecimal getAverageValue(String merchant, LocalDateTime start, LocalDateTime end) {
        BigDecimal avg = null;
        try {
            avg = service.getTransactionService().getAverageTransactionValue(merchant, start, end);

        } catch (ServiceException e) {
            System.out.println("Can't get data from server. Please, try later!");
        }
        return avg;
    }
}

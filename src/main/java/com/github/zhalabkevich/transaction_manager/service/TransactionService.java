package com.github.zhalabkevich.transaction_manager.service;

import com.github.zhalabkevich.transaction_manager.bean.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {

    BigDecimal getAverageTransactionValue(String merchant, LocalDateTime start, LocalDateTime end) throws ServiceException;

    int getTotalNumberOfTransactions(String merchant, LocalDateTime start, LocalDateTime end) throws ServiceException;

    List<Transaction> getReversalTransactions(String merchant, LocalDateTime start) throws ServiceException;

    List<Transaction> getTransactionsByMerchantAndPeriod(String merchant, LocalDateTime start, LocalDateTime end) throws ServiceException;

    List<Transaction> getActualTransactions(List<Transaction> allTransactions, List<Transaction> reversalTransactions) throws ServiceException;

}

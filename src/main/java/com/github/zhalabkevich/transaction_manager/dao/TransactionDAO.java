package com.github.zhalabkevich.transaction_manager.dao;

import com.github.zhalabkevich.transaction_manager.bean.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionDAO {
    List<Transaction> getTransactionsByMerchant(String merchant) throws DAOException;
    List<Transaction> getTransactionsByPeriod(LocalDateTime start, LocalDateTime end) throws DAOException;
    List<Transaction> getTransactionsByMerchantAndPeriod(String merchant, LocalDateTime start, LocalDateTime end) throws DAOException;
    List<Transaction> getReversalTransactionAfterDate(String merchant, LocalDateTime start) throws DAOException;
    Transaction getTransactionById(String id) throws DAOException;
}

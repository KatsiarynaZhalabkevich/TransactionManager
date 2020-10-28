package com.github.zhalabkevich.transaction_manager.service.impl;

import com.github.zhalabkevich.transaction_manager.bean.Transaction;
import com.github.zhalabkevich.transaction_manager.dao.DAOException;
import com.github.zhalabkevich.transaction_manager.dao.DAOFactory;
import com.github.zhalabkevich.transaction_manager.service.ServiceException;
import com.github.zhalabkevich.transaction_manager.service.TransactionService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private final DAOFactory dao = DAOFactory.getInstance();

    @Override
    public BigDecimal getAverageTransactionValue(String merchant,
                                                 LocalDateTime start,
                                                 LocalDateTime end) throws ServiceException {
        List<Transaction> actualTransactionList = getActualTransactions(
                getTransactionsByMerchantAndPeriod(merchant, start, end),
                getReversalTransactions(merchant, start));
        BigDecimal avgValue = actualTransactionList.stream()
                .map(Transaction::getAmount)
                .reduce(new BigDecimal("0.0"), (BigDecimal::add));
        return actualTransactionList.size() != 0
                ? avgValue.divide(BigDecimal.valueOf(actualTransactionList.size()), 2, RoundingMode.HALF_UP)
                : BigDecimal.valueOf(0);
    }

    @Override
    public int getTotalNumberOfTransactions(String merchant,
                                            LocalDateTime start,
                                            LocalDateTime end) throws ServiceException {
        List<Transaction> actualTransactionList = getActualTransactions(
                getTransactionsByMerchantAndPeriod(merchant, start, end),
                getReversalTransactions(merchant, start));

        return actualTransactionList.size();
    }

    @Override
    public List<Transaction> getReversalTransactions(String merchant, LocalDateTime start) throws ServiceException {
        List<Transaction> reversalTransactions;
        try {
            reversalTransactions = dao.getTransactionDao().getReversalTransactionAfterDate(merchant, start);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reversalTransactions;
    }

    @Override
    public List<Transaction> getTransactionsByMerchantAndPeriod(String merchant,
                                                                LocalDateTime start,
                                                                LocalDateTime end) throws ServiceException {
        List<Transaction> reversalTransactions;
        try {
            reversalTransactions = dao.getTransactionDao().getTransactionsByMerchantAndPeriod(merchant, start, end);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return reversalTransactions;
    }

    @Override
    public List<Transaction> getActualTransactions(List<Transaction> allTransactions,
                                                   List<Transaction> reversalTransactions) {
        List<Transaction> actualList = new ArrayList<>();
        for (Transaction allTr : allTransactions) {
            for (Transaction reversalTr : reversalTransactions) {
                if (!allTr.getId().equals(reversalTr.getRelatedTransaction().getId())) {
                    actualList.add(allTr);
                }
            }
        }

        return actualList;
    }
}

package com.github.zhalabkevich.transaction_manager.dao.impl;

import com.github.zhalabkevich.transaction_manager.bean.Transaction;
import com.github.zhalabkevich.transaction_manager.bean.Type;
import com.github.zhalabkevich.transaction_manager.dao.DAOException;
import com.github.zhalabkevich.transaction_manager.dao.TransactionDAO;
import com.github.zhalabkevich.transaction_manager.dao.util.CSVReader;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CSVTransactionDAO implements TransactionDAO {

    private final CSVReader reader = CSVReader.getInstance();
    private final String PATH;

    public CSVTransactionDAO() {
        ResourceBundle resource = ResourceBundle.getBundle("prop");
        this.PATH = resource.getString("prop.path").concat(resource.getString("prop.fileName")).concat(resource.getString("prop.postfix"));
    }

    @Override
    public List<Transaction> getTransactionsByMerchant(String merchant) throws DAOException {
       var transactionsByMerchant = new ArrayList<Transaction>();
        List<Transaction> list;
        try {
            list = reader.readCSVFile(PATH);
        } catch (IOException e) {
            throw new DAOException(e);
        }
         for (Transaction transaction : list) {
            if (merchant.equals(transaction.getMerchant())) {
                transactionsByMerchant.add(transaction);
            }
        }
        return transactionsByMerchant;
    }

    @Override
    public List<Transaction> getTransactionsByPeriod(LocalDateTime start, LocalDateTime end) throws DAOException {
        var transactionsByPeriod = new ArrayList<Transaction>();
        List<Transaction> list;
        try {
            list = reader.readCSVFile(PATH);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        for (Transaction transaction : list) {
            if (start.isBefore(transaction.getDate()) && end.isAfter(transaction.getDate())) {
                transactionsByPeriod.add(transaction);
            }
        }
        return transactionsByPeriod;
    }

    @Override
    public List<Transaction> getTransactionsByMerchantAndPeriod(String merchant,
                                                                LocalDateTime start,
                                                                LocalDateTime end) throws DAOException {
        var transactionsByMerchantAndPeriod = new ArrayList<Transaction>();
        List<Transaction> list;
        try {
            list = reader.readCSVFile(PATH);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        for (Transaction transaction : list) {
            if (merchant.equals(transaction.getMerchant())
                    && start.isBefore(transaction.getDate())
                    && end.isAfter(transaction.getDate())) {
                transactionsByMerchantAndPeriod.add(transaction);
            }
        }
        return transactionsByMerchantAndPeriod;
    }

    @Override
    public List<Transaction> getReversalTransactionAfterDate(String merchant,
                                                             LocalDateTime start) throws DAOException {
        var relatedTransactionsAfterDate = new ArrayList<Transaction>();
        List<Transaction> list;
        try {
            list = reader.readCSVFile(PATH);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        for (Transaction transaction : list) {
            if (Type.REVERSAL.equals(transaction.getType())
                    && merchant.equals(transaction.getMerchant())
                    && start.isBefore(transaction.getDate())) {
                relatedTransactionsAfterDate.add(transaction);
            }
        }
        return relatedTransactionsAfterDate;
    }

    @Override
    public Transaction getTransactionById(String id) throws DAOException {
        List<Transaction> list;
        try {
            list = reader.readCSVFile(PATH);
        } catch (IOException e) {
            throw new DAOException(e);
        }
        for (Transaction transaction : list) {
            if (id.equals(transaction.getId())) {
                return transaction;
            }
        }
        return null;
    }

}

package com.github.zhalabkevich.transaction_manager.dao;

import com.github.zhalabkevich.transaction_manager.dao.impl.CSVTransactionDAO;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        return instance;
    }

    private final TransactionDAO transactionDao = new CSVTransactionDAO();

    public TransactionDAO getTransactionDao() {
        return transactionDao;
    }
}

package com.github.zhalabkevich.transaction_manager.service;

import com.github.zhalabkevich.transaction_manager.service.impl.TransactionServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    private TransactionService service = new TransactionServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public TransactionService getTransactionService() {
        return service;
    }

}

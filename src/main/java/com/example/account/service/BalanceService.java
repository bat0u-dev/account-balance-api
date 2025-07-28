package com.example.account.service;

import com.example.account.dto.*;

import java.util.List;

public interface BalanceService {

    BalanceResponse getBalance(String name);
    BalanceResponse createBalance(CreateBalanceRequest request);
    TransactionResponse addTransaction(String name, CreateTransactionRequest req);
    List<TransactionResponse> listTransactions(String balanceName);
}

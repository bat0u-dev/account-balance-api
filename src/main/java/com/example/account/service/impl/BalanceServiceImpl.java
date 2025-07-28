package com.example.account.service.impl;

import com.example.account.dto.*;
import com.example.account.entity.*;
import com.example.account.repository.*;
import com.example.account.service.BalanceService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepo;
    private final TransactionRepository transactionRepo;

    public BalanceServiceImpl(BalanceRepository balanceRepo, TransactionRepository transactionRepo) {
        this.balanceRepo = balanceRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public BalanceResponse getBalance(String name) {
        Balance balance = balanceRepo.findByName(name).orElseThrow();
        BigDecimal totalUsd = balance.getTransactions().stream()
            .map(tx -> convertToUsd(tx.getAmount(), tx.getCurrency()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new BalanceResponse(balance.getName(), totalUsd, Currency.USD);
    }

    @Override
    @Transactional
    public BalanceResponse createBalance(CreateBalanceRequest request) {
        Balance balance = new Balance();
        balance.setName(request.name());
        balance.setCurrency(Currency.USD);
        balanceRepo.save(balance);
        return new BalanceResponse(balance.getName(), BigDecimal.ZERO, Currency.USD);
    }

    @Override
    @Transactional
    public TransactionResponse addTransaction(String balanceName, CreateTransactionRequest req) {

        // Idempotency check
        if (transactionRepo.existsByExternalId(req.externalId())) {
            return transactionRepo.findByExternalId(req.externalId())
                .map(tx -> new TransactionResponse(tx.getType(), tx.getAmount(), tx.getCurrency(), tx.getTimestamp()))
                .orElseThrow();
        }

        Balance balance = balanceRepo.findByName(balanceName).orElseThrow();

        Transaction tx = new Transaction();
        tx.setType(req.type());
        tx.setAmount(req.amount());
        tx.setCurrency(req.currency());
        tx.setTimestamp(LocalDateTime.now());
        tx.setBalance(balance);
        tx.setExternalId(req.externalId());

        if (req.type() == TransactionType.WITHDRAW) {
            BigDecimal current = getBalance(balanceName).total();
            BigDecimal withdrawUsd = convertToUsd(req.amount(), req.currency());
            if (withdrawUsd.compareTo(current) > 0) throw new RuntimeException("Insufficient funds");
        }

        transactionRepo.save(tx);
        return new TransactionResponse(tx.getType(), tx.getAmount(), tx.getCurrency(), tx.getTimestamp());
    }

    @Override
    public List<TransactionResponse> listTransactions(String balanceName) {
        Balance balance = balanceRepo.findByName(balanceName).orElseThrow();
        return balance.getTransactions().stream()
            .map(tx -> new TransactionResponse(tx.getType(), tx.getAmount(), tx.getCurrency(), tx.getTimestamp()))
            .toList();
    }

    private BigDecimal convertToUsd(BigDecimal amount, Currency currency) {
        return BigDecimal.valueOf(currency.getRateToUsd()).multiply(amount);
    }
}

package com.example.account.service.impl;

import com.example.account.dto.*;
import com.example.account.entity.*;
import com.example.account.entity.Currency;
import com.example.account.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BalanceServiceImplTest {

    @Mock
    BalanceRepository balanceRepo;
    @Mock
    TransactionRepository transactionRepo;

    @InjectMocks
    BalanceServiceImpl service;

    Balance testBalance;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testBalance = new Balance();
        testBalance.setId(UUID.randomUUID());
        testBalance.setName("test");
        testBalance.setCurrency(Currency.USD);
    }

    @Test
    void testCreateBalance() {
        when(balanceRepo.save(any())).thenReturn(testBalance);
        BalanceResponse response = service.createBalance(new CreateBalanceRequest("test"));
        assertEquals("test", response.name());
        assertEquals(BigDecimal.ZERO, response.total());
    }

    @Test
    void testGetBalance_NoTransactions() {
        testBalance.setTransactions(Collections.emptyList());
        when(balanceRepo.findByName("test")).thenReturn(Optional.of(testBalance));
        BalanceResponse response = service.getBalance("test");
        assertEquals(BigDecimal.ZERO, response.total());
    }

    @Test
    void testAddDepositTransaction() {
        when(balanceRepo.findByName("test")).thenReturn(Optional.of(testBalance));
        CreateTransactionRequest request = new CreateTransactionRequest(TransactionType.DEPOSIT, BigDecimal.valueOf(100), Currency.EUR, "test-ext-id-100");
        TransactionResponse txResponse = service.addTransaction("test", request);
        assertEquals(TransactionType.DEPOSIT, txResponse.type());
        assertEquals(BigDecimal.valueOf(100), txResponse.amount());
    }

    @Test
    void testAddWithdrawTransaction_InsufficientFunds() {
        when(balanceRepo.findByName("test")).thenReturn(Optional.of(testBalance));
        CreateTransactionRequest request = new CreateTransactionRequest(TransactionType.WITHDRAW, BigDecimal.valueOf(500), Currency.USD, "test-ext-id-500");
        assertThrows(RuntimeException.class, () -> service.addTransaction("test", request));
    }
}

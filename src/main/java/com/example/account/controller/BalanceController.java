package com.example.account.controller;

import com.example.account.dto.*;
import com.example.account.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/balances")
public class BalanceController {

    private final BalanceService service;

    @PostMapping
    public ResponseEntity<BalanceResponse> create(@RequestBody CreateBalanceRequest request) {
        return ResponseEntity.ok(service.createBalance(request));
    }

    @GetMapping("/{name}")
    public ResponseEntity<BalanceResponse> get(@PathVariable String name) {
        return ResponseEntity.ok(service.getBalance(name));
    }

    @PostMapping("/{balanceName}/transactions")
    public ResponseEntity<TransactionResponse> add(@PathVariable String balanceName, @RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok(service.addTransaction(balanceName, request));
    }

    @GetMapping("/{balanceName}/transactions")
    public ResponseEntity<List<TransactionResponse>> list(@PathVariable String balanceName) {
        return ResponseEntity.ok(service.listTransactions(balanceName));
    }
}

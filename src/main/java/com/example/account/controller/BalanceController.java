package com.example.account.controller;

import com.example.account.dto.*;
import com.example.account.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balances")
public class BalanceController {

    private final BalanceService service;

    public BalanceController(BalanceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<BalanceResponse> create(@RequestBody CreateBalanceRequest request) {
        return ResponseEntity.ok(service.createBalance(request));
    }

    @GetMapping("/{name}")
    public ResponseEntity<BalanceResponse> get(@PathVariable String name) {
        return ResponseEntity.ok(service.getBalance(name));
    }

    @PostMapping("/{name}/transactions")
    public ResponseEntity<TransactionResponse> add(@PathVariable String name, @RequestBody CreateTransactionRequest request) {
        return ResponseEntity.ok(service.addTransaction(name, request));
    }

    @GetMapping("/{name}/transactions")
    public ResponseEntity<List<TransactionResponse>> list(@PathVariable String name) {
        return ResponseEntity.ok(service.listTransactions(name));
    }
}

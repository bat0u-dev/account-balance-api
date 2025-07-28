package com.example.account.dto;

import com.example.account.entity.Currency;
import com.example.account.entity.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Schema(description = "Запрос на создание транзакции")
public record CreateTransactionRequest(
    @NotNull @Schema(example = "DEPOSIT") TransactionType type,
    @NotNull @Schema(example = "100.0") BigDecimal amount,
    @NotNull @Schema(example = "USD") Currency currency,
    @NotBlank @Schema(example = "txn-123456") String externalId
) {}

package com.example.account.dto;

import com.example.account.entity.Currency;
import com.example.account.entity.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "Ответ с деталями транзакции")
public record TransactionResponse(
    @Schema(example = "DEPOSIT") TransactionType type,
    @Schema(example = "100.0") BigDecimal amount,
    @Schema(example = "USD") Currency currency,
    @Schema(example = "2025-07-28T12:00:00") LocalDateTime timestamp
) {}

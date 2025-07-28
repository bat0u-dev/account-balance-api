package com.example.account.dto;

import com.example.account.entity.Currency;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Ответ с текущим балансом")
public record BalanceResponse(
    @Schema(example = "main-wallet") String name,
    @Schema(example = "100.00") BigDecimal total,
    @Schema(example = "USD") Currency currency
) {}

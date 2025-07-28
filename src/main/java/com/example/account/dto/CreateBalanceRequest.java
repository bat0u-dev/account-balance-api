package com.example.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Запрос на создание баланса")
public record CreateBalanceRequest(
    @NotBlank
    @Schema(example = "main-wallet", description = "Уникальное имя баланса")
    String name
) {}

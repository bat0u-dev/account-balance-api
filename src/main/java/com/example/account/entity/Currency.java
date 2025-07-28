package com.example.account.entity;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Доступные валюты", example = "USD")
public enum Currency {
    USD(1.0), EUR(1.1), BYN(0.3), RUB(0.015);

    private final double rateToUsd;

    Currency(double rateToUsd) {
        this.rateToUsd = rateToUsd;
    }

    public double getRateToUsd() {
        return rateToUsd;
    }
}

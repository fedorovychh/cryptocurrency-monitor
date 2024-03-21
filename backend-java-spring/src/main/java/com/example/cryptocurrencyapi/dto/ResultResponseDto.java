package com.example.cryptocurrencyapi.dto;

import lombok.Data;

@Data
public class ResultResponseDto {
    private double openPrice;
    private double closePrice;
    private int numberOfTransactions;
}

package com.example.cryptocurrencyapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

@Data
public class Result {
    @JsonSetter("v")
    private double tradingVolume;
    @JsonSetter("o")
    private double openPrice;
    @JsonSetter("c")
    private double closePrice;
    @JsonSetter("h")
    private int highestPrice;
    @JsonSetter("l")
    private double lowestPrice;
    @JsonSetter("n")
    private int numberOfTransactions;
}

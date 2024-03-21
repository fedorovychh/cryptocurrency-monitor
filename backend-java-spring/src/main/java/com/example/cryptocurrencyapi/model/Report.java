package com.example.cryptocurrencyapi.model;

import java.util.List;
import lombok.Data;

@Data
public class Report {
    private String ticker;
    private int queryCount;
    private int resultsCount;
    private boolean adjusted;
    private List<Result> results;
    private String status;
    private int count;
}

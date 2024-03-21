package com.example.cryptocurrencyapi.dto;

import com.example.cryptocurrencyapi.model.Result;
import java.util.ArrayList;
import lombok.Data;

@Data
public class ReportResponseDto {
    private String ticker;
    private ArrayList<Result> results;
    private int count;
}

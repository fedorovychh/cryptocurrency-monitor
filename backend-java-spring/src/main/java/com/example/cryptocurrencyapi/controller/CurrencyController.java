package com.example.cryptocurrencyapi.controller;

import com.example.cryptocurrencyapi.dto.ResultResponseDto;
import com.example.cryptocurrencyapi.service.result.ResultService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/results")
@CrossOrigin(origins = {
        "http://localhost:8081",
        "http://192.168.0.149:8081"
})
public class CurrencyController {
    private final ResultService resultService;

    @GetMapping
    public List<ResultResponseDto> findAllByDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return resultService.findAllByDates(startDate, endDate);
    }
}

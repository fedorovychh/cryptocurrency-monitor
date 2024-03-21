package com.example.cryptocurrencyapi.service.result;

import com.example.cryptocurrencyapi.dto.ResultResponseDto;
import java.time.LocalDate;
import java.util.List;

public interface ResultService {
    List<ResultResponseDto> findAllByDates(LocalDate startDate, LocalDate endDate);
}

package com.example.cryptocurrencyapi.service.report;

import com.example.cryptocurrencyapi.dto.ReportResponseDto;
import java.time.LocalDate;

public interface ReportService {
    ReportResponseDto findByDates(LocalDate startDate, LocalDate endDate);
}

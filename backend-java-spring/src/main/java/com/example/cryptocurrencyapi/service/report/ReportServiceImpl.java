package com.example.cryptocurrencyapi.service.report;

import com.example.cryptocurrencyapi.client.PolygonApiClient;
import com.example.cryptocurrencyapi.dto.ReportResponseDto;
import com.example.cryptocurrencyapi.mapper.ReportMapper;
import com.example.cryptocurrencyapi.model.Report;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final PolygonApiClient polygonApiClient;
    private final ObjectMapper objectMapper;
    private final ReportMapper reportMapper;

    @Override
    public ReportResponseDto findByDates(LocalDate startDate, LocalDate endDate) {
        String jsonObject = polygonApiClient.getExchangeRatesJson(startDate, endDate);
        Report report;
        try {
            report = objectMapper.readValue(jsonObject, Report.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't parse response to a Java object!", e);
        }
        return reportMapper.toDto(report);
    }
}

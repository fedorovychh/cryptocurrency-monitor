package com.example.cryptocurrencyapi.service.result;

import com.example.cryptocurrencyapi.dto.ResultResponseDto;
import com.example.cryptocurrencyapi.mapper.ResultMapper;
import com.example.cryptocurrencyapi.service.report.ReportService;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ReportService reportService;
    private final ResultMapper resultMapper;

    @Override
    public List<ResultResponseDto> findAllByDates(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Can't proceed these dates. "
                    + "End date should be equal or after start date!");
        }
        return reportService.findByDates(startDate, endDate)
                .getResults().stream()
                .map(resultMapper::toDto)
                .toList();
    }
}

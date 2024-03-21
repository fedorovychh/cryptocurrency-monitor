package com.example.cryptocurrencyapi.mapper;

import com.example.cryptocurrencyapi.config.MapperConfig;
import com.example.cryptocurrencyapi.dto.ReportResponseDto;
import com.example.cryptocurrencyapi.model.Report;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ReportMapper {
    ReportResponseDto toDto(Report report);
}

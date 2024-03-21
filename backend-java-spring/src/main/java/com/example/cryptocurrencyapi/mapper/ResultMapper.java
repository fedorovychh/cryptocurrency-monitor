package com.example.cryptocurrencyapi.mapper;

import com.example.cryptocurrencyapi.config.MapperConfig;
import com.example.cryptocurrencyapi.dto.ResultResponseDto;
import com.example.cryptocurrencyapi.model.Result;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface ResultMapper {
    ResultResponseDto toDto(Result result);
}

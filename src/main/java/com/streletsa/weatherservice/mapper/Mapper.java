package com.streletsa.weatherservice.mapper;

public interface Mapper<ENTITY, DTO> {
    DTO toDto(ENTITY entity);
}

package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {
    public CityDto() {}

    public CityDto(Long id, String cityName) {
        this.id = id;
        this.cityName = cityName;
    }

    private Long id;
    private String cityName;
}

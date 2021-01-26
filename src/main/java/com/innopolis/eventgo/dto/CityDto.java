package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityDto {
    public CityDto() {}

    public CityDto(Long id, String cityName, String shortName) {
        this.id = id;
        this.cityName = cityName;
        this.shortName = shortName;
    }

    private Long id;
    private String cityName;
    private String shortName;
}

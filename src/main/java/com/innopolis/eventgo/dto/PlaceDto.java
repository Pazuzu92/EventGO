package com.innopolis.eventgo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlaceDto {
    public PlaceDto() {}

    public PlaceDto(Long id, String street, String house, String number, CityDto city) {
        this.id = id;
        this.street = street;
        this.house = house;
        this.number = number;
        this.city = city;
    }

    private Long id;
    private String street;
    private String house;
    private String number;
    private CityDto city;
}

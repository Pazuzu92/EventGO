package com.innopolis.eventgo.dto;

import lombok.Data;

@Data
public class PlaceDto {
    private Long id;
    private String street;
    private String house;
    private String number;
    private CityDto city;
}

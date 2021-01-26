package com.innopolis.eventgo.service;

import com.innopolis.eventgo.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();

    List<CityDto> findAllExceptBy(String name);

    CityDto findByShortName(String shortName);
}

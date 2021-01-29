package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.dto.CityDto;

import java.util.List;

public interface CityService {

    List<CityDto> findAll();

    List<CityDto> findAllExceptBy(String name);

    City findByShortName(String shortName);
}

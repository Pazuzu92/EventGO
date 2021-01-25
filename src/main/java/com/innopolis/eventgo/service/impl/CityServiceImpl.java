package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.repository.CityDAO;
import com.innopolis.eventgo.dto.CityDto;
import com.innopolis.eventgo.service.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private final CityDAO cityDAO;
    private final ModelMapper modelMapper = new ModelMapper();

    public CityServiceImpl(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<CityDto> findAll() {
        List<City> cityList = cityDAO.findAll();
        return cityList.stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .collect(Collectors.toList());
    }
}

package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.repository.CityDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityDAO cityDAO;

    public CityService(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    public List<City> findAll(){
        return cityDAO.findAll();
    }
}

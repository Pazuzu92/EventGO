package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CityDAO extends JpaRepository<City, Long> {

    City findByCityName(String cityName);
}

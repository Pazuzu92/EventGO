package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityDAO extends JpaRepository<City, Long> {

    Optional<City> findByCityName(String cityName);
    Optional<City> findByShortName(String cityName);
}

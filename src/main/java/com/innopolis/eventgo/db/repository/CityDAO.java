package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityDAO extends JpaRepository<City, Long> {
}

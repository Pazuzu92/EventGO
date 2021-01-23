package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends JpaRepository<City, Long> {
}

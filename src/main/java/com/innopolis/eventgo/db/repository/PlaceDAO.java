package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceDAO extends JpaRepository<Place, Long> {
    List<Place> findByCity(City city);
}

package com.innopolis.eventgo.db.repository;

import com.innopolis.eventgo.db.entity.City;
import com.innopolis.eventgo.db.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceDAO extends JpaRepository<Place, Long> {
    List<Place> findByCity(City city);
}

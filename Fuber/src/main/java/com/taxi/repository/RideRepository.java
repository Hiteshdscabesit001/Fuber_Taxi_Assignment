package com.taxi.repository;

import com.taxi.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// RideRepository.java
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findByTaxiId(Long taxiId);

    List<Ride> findByTaxiColorAndDurationGreaterThanEqual(String taxiColor, int duration);

    @Query("SELECT AVG(r.duration) FROM Ride r WHERE r.taxi.color = :color")
    Double getAverageDurationForColor(@Param("color") String color);


}


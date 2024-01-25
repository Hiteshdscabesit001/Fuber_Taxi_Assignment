package com.taxi.repository;

import com.taxi.entity.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxiRepository extends JpaRepository<Taxi, Long> {
    List<Taxi> findByAvailableAndColor(boolean available, String color);
    List<Taxi> findByColor(String color);

    String getColor();
}



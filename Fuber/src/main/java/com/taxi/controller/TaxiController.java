package com.taxi.controller;

import com.taxi.entity.Taxi;
import com.taxi.service.FuberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/taxis")
public class TaxiController {
    @Autowired
    private FuberService fuberService;

    @PostMapping("/request")
    public ResponseEntity<Taxi> requestTaxi(@RequestParam double customerLatitude,
                                            @RequestParam double customerLongitude,
                                            @RequestParam boolean pinkPreference) {
        Taxi taxi = fuberService.requestTaxi(customerLatitude, customerLongitude, pinkPreference);
        return new ResponseEntity<>(taxi, HttpStatus.OK);
    }

    @PostMapping("/endRide/{taxiId}")
    public ResponseEntity<String> endRide(@PathVariable Long taxiId,
                                          @RequestParam double endLatitude,
                                          @RequestParam double endLongitude,
                                          @RequestParam int duration) {
        fuberService.endRide(taxiId, endLatitude, endLongitude, duration);
        return new ResponseEntity<>("Ride ended successfully", HttpStatus.OK);
    }
}


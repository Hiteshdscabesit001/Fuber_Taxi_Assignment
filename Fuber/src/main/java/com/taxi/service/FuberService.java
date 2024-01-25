package com.taxi.service;


import com.taxi.entity.Ride;
import com.taxi.entity.Taxi;
import com.taxi.exception.NoAvailableTaxisException;
import com.taxi.exception.TaxiNotFoundException;
import com.taxi.repository.RideRepository;
import com.taxi.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuberService {
    @Autowired
    private TaxiRepository taxiRepository;

    @Autowired
    private RideRepository rideRepository;

    public Taxi requestTaxi(double customerLatitude, double customerLongitude, boolean pinkPreference) {
        List<Taxi> availableTaxis = taxiRepository.findByAvailableAndColor(true, pinkPreference ? "pink" : "");
        if (availableTaxis.isEmpty()) {
            throw new NoAvailableTaxisException("No available taxis at the moment. Please try again later.");
        }

        Taxi nearestTaxi = findNearestTaxi(availableTaxis, customerLatitude, customerLongitude);
        nearestTaxi.setAvailable(false);
        taxiRepository.save(nearestTaxi);

        return nearestTaxi;
    }


    private Taxi findNearestTaxi(List<Taxi> taxis, double customerLatitude, double customerLongitude) {
        double minDistance = Double.MAX_VALUE;
        Taxi nearestTaxi = null;

        for (Taxi taxi : taxis) {
            double distance = calculateDistance(taxi.getLatitude(), taxi.getLongitude(), customerLatitude, customerLongitude);
            if (distance < minDistance) {
                minDistance = distance;
                nearestTaxi = taxi;
            }
        }

        return nearestTaxi;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double deltaX = lat1 - lat2;
        double deltaY = lon1 - lon2;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }


    public void endRide(Long taxiId, double endLatitude, double endLongitude, int duration) {
        Taxi taxi = taxiRepository.findById(taxiId)
                .orElseThrow(() -> new TaxiNotFoundException("Taxi not found"));

        Ride ride = new Ride();
        ride.setTaxi(taxi);
        ride.setStartLatitude(taxi.getLatitude());
        ride.setStartLongitude(taxi.getLongitude());
        ride.setEndLatitude(endLatitude);
        ride.setEndLongitude(endLongitude);
        ride.setDuration(duration);

        taxi.setAvailable(true);
        taxiRepository.save(taxi);

        double totalAmount = calculateTotalAmount(duration, ride.calculateDistance());
        System.out.println("Total amount owed: " + totalAmount + " doge coin");

        rideRepository.save(ride);
    }

    private double calculateTotalAmount(int duration, double distance) {
        double baseFare = duration * 1; // 1 dogecoin per minute
        double distanceFare = distance * 2; // 2 dogecoin per kilometer

        // Additional fare for pink cars
        if ("pink".equals(taxiRepository.getColor())) {
            distanceFare += 5; // Additional 5 dogecoin for pink cars
        }

        return baseFare + distanceFare;
    }
}


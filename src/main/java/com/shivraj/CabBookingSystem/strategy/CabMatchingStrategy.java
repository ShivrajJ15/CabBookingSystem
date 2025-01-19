package com.shivraj.CabBookingSystem.strategy;

import com.shivraj.CabBookingSystem.model.Cab;
import com.shivraj.CabBookingSystem.model.Location;
import com.shivraj.CabBookingSystem.model.Rider;

import java.util.List;
import java.util.Optional;


public interface CabMatchingStrategy {
    Optional<Cab> matchCabToRider(Rider rider, List<Cab> availableCabs, Location starting, Location ending);
}

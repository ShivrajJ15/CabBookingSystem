package com.shivraj.CabBookingSystem.strategy;

import com.shivraj.CabBookingSystem.model.Cab;
import com.shivraj.CabBookingSystem.model.Location;
import com.shivraj.CabBookingSystem.model.Rider;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DefaultCabMatchingStrategy implements CabMatchingStrategy{
    @Override
    public Optional<Cab> matchCabToRider(
            @NonNull final Rider rider,
            @NonNull final List<Cab> availableCabs,
            @NonNull final Location starting,
            @NonNull final Location ending) {

        return availableCabs.stream().filter(cab -> cab.getCurrentTrip()==null).findAny();
    }
}

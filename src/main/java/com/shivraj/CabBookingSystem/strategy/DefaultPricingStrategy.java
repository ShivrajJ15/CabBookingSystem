package com.shivraj.CabBookingSystem.strategy;

import com.shivraj.CabBookingSystem.model.Location;
import org.springframework.stereotype.Service;

@Service
public class DefaultPricingStrategy implements PricingStrategy{

    public static final Double PER_KM_RATE=10.0;

    @Override
    public Double findPrice(Location starting, Location ending) {
        return starting.distance(ending) + PER_KM_RATE;
    }
}

package com.shivraj.CabBookingSystem.strategy;

import com.shivraj.CabBookingSystem.model.Location;



public interface PricingStrategy {

    Double findPrice(Location starting, Location ending);
}

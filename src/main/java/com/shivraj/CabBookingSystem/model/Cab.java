package com.shivraj.CabBookingSystem.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public class Cab {

    String id;
    String driverName;

    @Setter
    Trip currentTrip;
    @Setter
    Location currentLocation;
    @Setter
    Boolean isAvailable =false;

    public Cab(String cabId, String cabDriverName) {
        this.id=cabId;
        this.driverName=cabDriverName;
    }
}

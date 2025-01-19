package com.shivraj.CabBookingSystem.model;


import lombok.NonNull;
import lombok.ToString;

enum TripStatus{
    IN_PROGRESS,
    FINISHED
}


@ToString
public class Trip {

    private Rider rider;
    private Cab cab;
    private TripStatus status;
    private Double price;
    private Location initialLocation;
    private Location finalLocation;

    public Trip(
            @NonNull
            final Rider rider,
            @NonNull
            final Cab cab,
            @NonNull
            final Double price,
            @NonNull
            final Location initialLocation,
            @NonNull
            final Location finalLocation) {
        this.rider = rider;
        this.cab = cab;
        this.status = TripStatus.IN_PROGRESS;
        this.price = price;
        this.initialLocation = initialLocation;
        this.finalLocation = finalLocation;
    }

    public void endTrip(){
        this.status=TripStatus.FINISHED;
    }

}

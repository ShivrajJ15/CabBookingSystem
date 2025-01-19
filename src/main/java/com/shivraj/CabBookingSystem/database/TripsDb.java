package com.shivraj.CabBookingSystem.database;

import com.shivraj.CabBookingSystem.exceptions.NoCabAvailableException;
import com.shivraj.CabBookingSystem.exceptions.TripNotFoundException;
import com.shivraj.CabBookingSystem.strategy.CabMatchingStrategy;
import com.shivraj.CabBookingSystem.strategy.PricingStrategy;
import lombok.NonNull;
import com.shivraj.CabBookingSystem.model.Cab;
import com.shivraj.CabBookingSystem.model.Location;
import com.shivraj.CabBookingSystem.model.Rider;
import com.shivraj.CabBookingSystem.model.Trip;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class TripsDb {
    private static final Double MAX_ALLOWED_MATCHING_DISTANCE=0.0;

    private Map<String, List<Trip>> trips=new HashMap<>();

    private CabsDb cabsDb;
    private RidersDb ridersDb;
    private CabMatchingStrategy cabMatchingStrategy;
    private PricingStrategy pricingStrategy;

    public TripsDb( CabsDb cabsDb,
                    CabMatchingStrategy cabMatchingStrategy,
                    RidersDb ridersDb,
                    PricingStrategy pricingStrategy) {

        this.cabsDb = cabsDb;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.ridersDb = ridersDb;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(
            @NonNull final Rider rider,
            @NonNull final Location starting,
            @NonNull final Location ending){

        final List<Cab> nearByCabs=cabsDb.getCabs(starting,MAX_ALLOWED_MATCHING_DISTANCE);
        final Optional<Cab> selectedCabs=cabMatchingStrategy.matchCabToRider(rider,nearByCabs,starting,ending);
        if(!selectedCabs.isPresent()){
            throw new NoCabAvailableException("No any cabs available nearby , try searching");
        }

        final Cab selectedCab=selectedCabs.get();
        final Double price=pricingStrategy.findPrice(starting,ending);
        final Trip newTrip=new Trip(rider,selectedCab,price,starting,ending);

        if(!trips.containsKey(rider.getId())){
            trips.put(rider.getId(),new ArrayList<>());
        }
        trips.get(rider.getId()).add(newTrip);
        selectedCab.setCurrentTrip(newTrip);
    }

    public List<Trip> tripHistory(@NonNull final Rider rider){
        return trips.get(rider.getId());
    }

    public void endTrip(@NonNull final Cab cab){
        if(cab.getCurrentTrip()==null){
            throw new TripNotFoundException("Trip not found with Cab : "+cab);
        }
        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }


}

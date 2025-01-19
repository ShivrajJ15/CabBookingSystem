package com.shivraj.CabBookingSystem.database;


import com.shivraj.CabBookingSystem.exceptions.CabAlreadyExistsException;
import com.shivraj.CabBookingSystem.exceptions.CabNotFoundException;
import lombok.NonNull;
import com.shivraj.CabBookingSystem.model.Cab;
import com.shivraj.CabBookingSystem.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CabsDb {

    Map<String, Cab> cabs=new HashMap<>();

    public void createCab(@NonNull final Cab newCab){
        if(cabs.containsKey(newCab.getId())){
            throw new CabAlreadyExistsException("Cab Already Exists");
        }
        cabs.put(newCab.getId(),newCab);
    }

    public Cab getCabById(@NonNull final String cabId){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException("Cab not found with id : "+cabId);
        }
        return cabs.get(cabId);
    }

    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException("Cab not found with id : "+cabId);
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCapAvailability(@NonNull final String cabId,@NonNull final Boolean newAvailability){
        if(!cabs.containsKey(cabId)){
            throw new CabNotFoundException("Cab not found with id : "+cabId);
        }
        cabs.get(cabId).setIsAvailable(newAvailability);
    }

    public List<Cab> getCabs(@NonNull final Location initialLocation,@NonNull final Double distance){
        List<Cab> availableCabs=new ArrayList<>();
        for(Cab cab:cabs.values()){
            if(cab.getIsAvailable() && cab.getCurrentLocation().distance(initialLocation)<=distance){
                availableCabs.add(cab);
            }
        }
        return availableCabs;
    }
}

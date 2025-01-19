package com.shivraj.CabBookingSystem.database;

import com.shivraj.CabBookingSystem.exceptions.RiderAlreadyExistsException;
import com.shivraj.CabBookingSystem.exceptions.RiderNotFoundException;
import lombok.NonNull;
import com.shivraj.CabBookingSystem.model.Rider;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RidersDb {
    Map<String, Rider> riders=new HashMap<>();

    public void createRider(@NonNull final Rider rider){
        if(riders.containsKey(rider.getId())) {
            throw new RiderAlreadyExistsException("Rider Already Exists with ID : "+rider.getId()+" Cant create Existing rider");
        }
        riders.put(rider.getId(),rider);
    }

    public Rider getRider(@NonNull final String riderId){
        if(!riders.containsKey(riderId)) {
            throw new RiderNotFoundException("Rider not found with Id : "+riderId);
        }
        return riders.get(riderId);
    }
}

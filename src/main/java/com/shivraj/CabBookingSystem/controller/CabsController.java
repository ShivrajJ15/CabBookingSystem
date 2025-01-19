package com.shivraj.CabBookingSystem.controller;

import com.shivraj.CabBookingSystem.database.CabsDb;
import com.shivraj.CabBookingSystem.database.TripsDb;
import com.shivraj.CabBookingSystem.model.Cab;
import com.shivraj.CabBookingSystem.model.Location;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class CabsController {

    private TripsDb tripsDb;
    private CabsDb cabsDb;


    public CabsController(TripsDb tripsDb, CabsDb cabsDb) {
        this.tripsDb = tripsDb;
        this.cabsDb = cabsDb;
    }

    @PostMapping("register/cab")
    public ResponseEntity<String> registerCab(final String cabId, final String cabDriverName){
        cabsDb.createCab(new Cab(cabId,cabDriverName));
        return new ResponseEntity<>("Cab Booked Successfully",HttpStatus.OK);
    }

    @PostMapping("/update/cab/location")
    public ResponseEntity<String> updateCabLocation(final String cabId,final Double newX,final Double newY){
        cabsDb.updateCabLocation(cabId,new Location(newX,newY));
        return new ResponseEntity<>("Cab location updated successfully",HttpStatus.OK);
    }

    @PostMapping("/update/cab/availability")
    public ResponseEntity<String> updateCabAvailability(final String cabId,final boolean newAvailability){
        cabsDb.updateCapAvailability(cabId,newAvailability);
        return new ResponseEntity<>("Cab Availability updated successfully",HttpStatus.OK);
    }

    @PostMapping("/update/cab/end/trip")
    public ResponseEntity<String> endTrip(final String cabId){
        tripsDb.endTrip(cabsDb.getCabById(cabId));
        return new ResponseEntity<>("Trip ended successfully for cab with Id "+cabId,HttpStatus.OK);
    }
}

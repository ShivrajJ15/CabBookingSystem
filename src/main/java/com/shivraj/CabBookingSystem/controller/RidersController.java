package com.shivraj.CabBookingSystem.controller;

import com.shivraj.CabBookingSystem.database.CabsDb;
import com.shivraj.CabBookingSystem.database.RidersDb;
import com.shivraj.CabBookingSystem.database.TripsDb;
import com.shivraj.CabBookingSystem.model.Location;
import com.shivraj.CabBookingSystem.model.Rider;
import com.shivraj.CabBookingSystem.model.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class RidersController {

    private RidersDb ridersDb;
    private TripsDb tripsDb;

    public RidersController(RidersDb ridersDb, TripsDb tripsDb) {
        this.ridersDb = ridersDb;
        this.tripsDb = tripsDb;
    }

    @PostMapping("register/rider")
    public ResponseEntity<String> registerRider(final String riderId,final String riderName){
        ridersDb.createRider(new Rider(riderId,riderName));
        return new ResponseEntity<>("Rider registered successfully", HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> book(final String riderId,final Double srcX, final  Double srcY,final Double destX,final Double destY){
        tripsDb.createTrip(ridersDb.getRider(riderId), new Location(srcX,srcY), new Location(destX,destY));
        return new ResponseEntity<>("Booking successfull",HttpStatus.OK);
    }

    @GetMapping("/book")
    public ResponseEntity<String> fetchHistory(final String riderId){
        List<Trip> trips=tripsDb.tripHistory(ridersDb.getRider(riderId));
        return new ResponseEntity<>("History Fetched Successfully",HttpStatus.OK);
    }

}

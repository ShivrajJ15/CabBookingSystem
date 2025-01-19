package com.shivraj.CabBookingSystem;

import com.shivraj.CabBookingSystem.controller.CabsController;
import com.shivraj.CabBookingSystem.controller.RidersController;
import com.shivraj.CabBookingSystem.database.CabsDb;
import com.shivraj.CabBookingSystem.database.RidersDb;
import com.shivraj.CabBookingSystem.database.TripsDb;
import com.shivraj.CabBookingSystem.exceptions.*;
import com.shivraj.CabBookingSystem.strategy.CabMatchingStrategy;
import com.shivraj.CabBookingSystem.strategy.DefaultCabMatchingStrategy;
import com.shivraj.CabBookingSystem.strategy.DefaultPricingStrategy;
import com.shivraj.CabBookingSystem.strategy.PricingStrategy;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRunner {

    CabsController cabsController;
    RidersController ridersController;

    @BeforeEach
    void setUp(){
        CabsDb cabsDb = new CabsDb();
        RidersDb ridersDb = new RidersDb();

        CabMatchingStrategy cabMatchingStrategy = new DefaultCabMatchingStrategy();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();

        TripsDb tripsDb = new TripsDb(cabsDb, cabMatchingStrategy, ridersDb, pricingStrategy);

        cabsController = new CabsController(tripsDb, cabsDb);
        ridersController = new RidersController(ridersDb, tripsDb);
    }

    @Test
    void testCabBookingDesign() {
        // Register Riders
        ridersController.registerRider("r1", "TestRider1");
        ridersController.registerRider("r2", "TestRider2");
        ridersController.registerRider("r3", "TestRider3");
        ridersController.registerRider("r4", "TestRider4");

        // Register Cabs
        cabsController.registerCab("c1", "Driver1");
        cabsController.registerCab("c2", "Driver2");
        cabsController.registerCab("c3", "Driver3");
        cabsController.registerCab("c4", "Driver4");

        // Update Cab Locations
        cabsController.updateCabLocation("c1", 1.0, 1.0); // Close to r1
        cabsController.updateCabLocation("c2", 2.0, 2.0); // Close to r2
        cabsController.updateCabLocation("c3", 3.0, 3.0); // Close to r3
        cabsController.updateCabLocation("c4", 4.0, 4.0); // Close to r4

        // Make some cabs unavailable
        cabsController.updateCabAvailability("c2", false); // c2 unavailable
        cabsController.updateCabAvailability("c4", false); // c4 unavailable

        // Booking Trips
        // r1 books a cab
        ridersController.book("r1", 0.0, 0.0, 5.0, 5.0); // c1 should be matched

        // r3 books a cab
        ridersController.book("r3", 3.5, 3.5, 10.0, 10.0); // c3 should be matched

        // Print Trip Details
        System.out.println("\n### Printing current trips for r1 and r3");
        System.out.println(ridersController.fetchHistory("r1").getBody());
        System.out.println(ridersController.fetchHistory("r3").getBody());

        // Update Cab Location
        cabsController.updateCabLocation("c3", 8.0, 8.0);

        System.out.println("\n### Printing current trips for r1 and r3 after location update");
        System.out.println(ridersController.fetchHistory("r1").getBody());
        System.out.println(ridersController.fetchHistory("r3").getBody());

        // End Trip
        cabsController.endTrip("c3");

        System.out.println("\n### Printing current trips for r1 and r3 after ending trip for r3");
        System.out.println(ridersController.fetchHistory("r1").getBody());
        System.out.println(ridersController.fetchHistory("r3").getBody());

        // Booking when no cab is available
        Assert.assertThrows(NoCabAvailableException.class, () -> {
            ridersController.book("r3", 0.0, 0.0, 900.0, 900.0);
        });

        // r4 books a cab
        ridersController.book("r4", 3.0, 3.0, 9.0, 9.0);

        System.out.println("\n### Printing trips for r1, r3, and r4");
        System.out.println(ridersController.fetchHistory("r1").getBody());
        System.out.println(ridersController.fetchHistory("r3").getBody());
        System.out.println(ridersController.fetchHistory("r4").getBody());
    }
}

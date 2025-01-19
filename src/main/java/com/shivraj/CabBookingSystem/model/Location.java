package com.shivraj.CabBookingSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

@ToString
@Getter
@AllArgsConstructor
public class Location {
    private double x;
    private double y;

    public double distance(Location location){
        return sqrt(pow(this.x-location.x,2)+pow(this.y-location.y,2));
    }
}

package com.mahesh.ioc.javabasedconfiguration;


public class Traveller {
    Vehicle vehicle = null;
    public Traveller(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startJourney(){
        this.vehicle.move();
    }
}

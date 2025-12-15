package com.mahesh.looseCoupling;

public class Traveller {
    Vehicle vehicle = null;

    public Traveller(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startJourney(){
        this.vehicle.move();
    }
}

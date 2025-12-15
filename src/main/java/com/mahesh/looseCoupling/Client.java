package com.mahesh.looseCoupling;

public class Client {


    static void main() {
        Vehicle vehicle = new Car();
        Vehicle bike = new Bike();


//        Traveller traveller = new Traveller(vehicle);
        Traveller traveller = new Traveller(bike);
        traveller.startJourney();


    }
}

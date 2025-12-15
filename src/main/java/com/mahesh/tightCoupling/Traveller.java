package com.mahesh.tightCoupling;

public class Traveller {

    Car car = null;
    Bike bike = null;

//    public Traveller(){
//        this.car = new Car();
//    }

    public Traveller(){
        this.bike = new Bike();
    }
    public void startJourney(){
        this.bike.move();
    }

//    public Traveller() {
//
//    }

    //    public void startJourney(){
//        this.car.move();
//    }






}

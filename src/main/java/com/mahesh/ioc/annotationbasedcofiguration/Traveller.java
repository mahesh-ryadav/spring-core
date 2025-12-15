package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Traveller {
    Vehicle vehicle = null;
    @Autowired
    public Traveller(@Qualifier("car") Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void startJourney(){
        this.vehicle.move();
    }
}

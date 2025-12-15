package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {
    @java.lang.Override
    @Autowired
    public void move() {
        System.out.println("Bike is moving");
    }
}

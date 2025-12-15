package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Cycle implements Vehicle {

    @java.lang.Override
    @Autowired
    public void move() {
        System.out.println("Cycle is moving");
    }
}

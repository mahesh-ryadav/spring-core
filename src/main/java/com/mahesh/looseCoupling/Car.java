package com.mahesh.looseCoupling;

public class Car implements Vehicle {

    @java.lang.Override
    public void move() {
        System.out.println("Car is moving");
    }
}

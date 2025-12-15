package com.mahesh.looseCoupling;

public class Bike implements Vehicle{
    @java.lang.Override
    public void move() {
        System.out.println("Bike is moving");
    }
}

package com.mahesh.ioc.javabasedconfiguration;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Client {


    static void main() {

//        Vehicle vehicle = new Car();
//        Vehicle bike = new Bike();
//        Traveller traveller = new Traveller(vehicle);
//        Traveller traveller = new Traveller(bike);
//        traveller.startJourney();

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = applicationContext.getBean(Car.class);
        car.move();

        Bike bike = applicationContext.getBean(Bike.class);
        bike.move();

        Cycle cycle = applicationContext.getBean(Cycle.class);
        cycle.move();

        Traveller traveller = applicationContext.getBean(Traveller.class);
        traveller.startJourney();
    }
}

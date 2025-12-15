# Tight Coupling vs Loose Coupling in Java

This project demonstrates the concepts of tight coupling and loose coupling in Java using a simple example of a `Traveller` class that uses different vehicles to start a journey.

## Table of Contents

- [Tight Coupling](#tight-coupling)
- [Loose Coupling](#loose-coupling)
- [Comparison](#comparison)
- [How to Run](#how-to-run)

## Tight Coupling

Tight coupling occurs when classes are highly dependent on each other. In this example, the `Traveller` class directly instantiates a `Bike` object.

### Code Example

**Traveller.java**
```java
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
```

**Bike.java**
```java
package com.mahesh.tightCoupling;

public class Bike {

    public void move(){
        System.out.println("Bike is moving.");
    }

}
```

**Car.java**
```java
package com.mahesh.tightCoupling;

public class Car {

    public void move(){
        System.out.println("Car is moving.");
    }

}
```

**Client.java**
```java
package com.mahesh.tightCoupling;

public class Client {

    public static void main(String[] args) {
        Traveller traveller = new Traveller();
        traveller.startJourney();
    }
}
```

### Explanation

In the tight coupling example, the `Traveller` class directly creates an instance of `Bike` in its constructor. This creates a strong dependency between `Traveller` and `Bike`. If we want to change the vehicle from `Bike` to `Car`, we need to modify the `Traveller` class itself. The commented-out code shows an alternative where `Traveller` could instantiate a `Car`, but it still requires changing the class.

### Disadvantages

- **Rigidity**: Hard to change or extend. To use a different vehicle, the `Traveller` class must be modified.
- **Difficulty in Testing**: Hard to unit test `Traveller` without involving `Bike`.
- **Poor Maintainability**: Changes in `Bike` (like method signatures) can break `Traveller`.
- **Limited Reusability**: `Traveller` is tightly bound to `Bike` and can't easily work with other vehicles.
- **Increased Complexity**: As the system grows, these dependencies create a web of interconnected classes.

## Loose Coupling

Loose coupling is achieved by using interfaces and dependency injection. The `Traveller` class depends on a `Vehicle` interface rather than a concrete implementation.

### Code Example

**Vehicle.java**
```java
package com.mahesh.looseCoupling;

public interface Vehicle {
    void move();
}
```

**Bike.java**
```java
package com.mahesh.looseCoupling;

public class Bike implements Vehicle{
    @java.lang.Override
    public void move() {
        System.out.println("Bike is moving");
    }
}
```

**Car.java**
```java
package com.mahesh.looseCoupling;

public class Car implements Vehicle {

    @java.lang.Override
    public void move() {
        System.out.println("Car is moving");
    }
}
```

**Cycle.java**
```java
package com.mahesh.looseCoupling;

public class Cycle implements Vehicle{

    @java.lang.Override
    public void move() {
        System.out.println("Cycle is moving");
    }
}
```

**Traveller.java**
```java
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
```

**Client.java**
```java
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
```

### Explanation

In the loose coupling example, we introduce a `Vehicle` interface that defines the contract for all vehicles. `Bike`, `Car`, and `Cycle` implement this interface. The `Traveller` class now accepts a `Vehicle` through its constructor (dependency injection) instead of creating one itself. This allows `Traveller` to work with any vehicle implementation without knowing the details.

The `Client` class demonstrates how to use this design. It creates different vehicle instances and injects them into `Traveller`. Note that the `main` method in `Client.java` is declared as `static void main()` instead of `public static void main(String[] args)`, which is a minor issue but doesn't affect the demonstration.

### Advantages

- **Flexibility**: Easy to switch implementations. We can pass any `Vehicle` to `Traveller`.
- **Ease of Testing**: Can use mocks and stubs for `Vehicle` in unit tests.
- **Improved Maintainability**: Changes to vehicle implementations don't affect `Traveller`.
- **Better Reusability**: `Traveller` can work with any vehicle that implements `Vehicle`.
- **Reduced Complexity**: Dependencies are managed through interfaces, making the system more modular.

## Comparison

| Aspect | Tight Coupling | Loose Coupling |
|--------|----------------|----------------|
| Dependency | Direct instantiation of concrete classes | Depends on abstractions (interfaces) |
| Flexibility | Low - hard to change vehicle type | High - easy to switch vehicle implementations |
| Testing | Difficult - requires real objects | Easy - can use mocks and stubs |
| Maintainability | Poor - changes ripple through system | Good - isolated changes |
| Reusability | Limited - bound to specific classes | High - works with any implementation |
| Extensibility | Hard - requires modifying dependent classes | Easy - just implement the interface |
| Code Changes | Frequent modifications needed | Minimal changes for new features |
| Design Principle | Violates Dependency Inversion | Follows SOLID principles |

### Key Differences in Code

1. **Instantiation**:
   - Tight: `Traveller` creates `Bike` internally
   - Loose: `Vehicle` is injected from outside

2. **Interface Usage**:
   - Tight: No abstraction, direct class references
   - Loose: Uses `Vehicle` interface for abstraction

3. **Constructor**:
   - Tight: Default constructor creates dependencies
   - Loose: Constructor takes dependencies as parameters

4. **Extending Functionality**:
   - Tight: Modify `Traveller` to add new vehicles
   - Loose: Just create new classes implementing `Vehicle`

## How to Run

1. Compile the Java files:
   ```
   javac -d . src/main/java/com/mahesh/tightCoupling/*.java
   javac -d . src/main/java/com/mahesh/looseCoupling/*.java
   ```

2. Run the tight coupling example:
   ```
   java com.mahesh.tightCoupling.Client
   ```

3. Run the loose coupling example:
   ```
   java com.mahesh.looseCoupling.Client
   ```

Note: The loose coupling `Client.java` has a `main()` method without proper signature. To run it, you may need to add `public static void main(String[] args)` or call it from another class.

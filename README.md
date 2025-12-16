# Tight Coupling vs Loose Coupling in Java

This project demonstrates the concepts of tight coupling and loose coupling in Java using a simple example of a `Traveller` class that uses different vehicles to start a journey.

## Table of Contents

- [Coupling](#coupling)
  - [What is Coupling?](#what-is-coupling)
  - [Tight Coupling](#tight-coupling)
  - [Loose Coupling](#loose-coupling)
  - [Comparison](#comparison)
  - [Interview Questions on Coupling](#interview-questions-on-coupling)
  - [How to Run](#how-to-run)
- [Inversion of Control (IoC)](#inversion-of-control-ioc)
  - [What is IoC?](#what-is-ioc)
  - [Annotation-based Configuration](#annotation-based-configuration)
  - [Java-based Configuration](#java-based-configuration)
  - [Interview Questions on IoC and Configuration](#interview-questions-on-ioc-and-configuration)
- [Stereotype Annotations](#stereotype-annotations)

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

## Interview Questions on Coupling

Here are some common interview questions related to coupling in software design, along with detailed answers:

### 1. What is coupling in software design?

**Answer:** Coupling refers to the degree of interdependence between software modules or classes. It measures how closely connected different parts of a system are. High coupling means modules are tightly connected and changes in one module can significantly affect others. Low coupling means modules are relatively independent, making the system more maintainable and flexible.

### 2. Explain the difference between tight coupling and loose coupling.

**Answer:** Tight coupling occurs when classes are highly dependent on each other, often through direct instantiation or concrete references. For example, if Class A directly creates an instance of Class B, they are tightly coupled.

Loose coupling is achieved when classes depend on abstractions (like interfaces) rather than concrete implementations. This allows for greater flexibility and easier changes. In the examples above, the tight coupling version has `Traveller` directly instantiating `Bike`, while the loose coupling version uses a `Vehicle` interface and dependency injection.

### 3. What are the disadvantages of tight coupling?

**Answer:** Tight coupling has several disadvantages:
- **Rigidity**: Hard to modify or extend code without affecting other parts
- **Difficulty in Testing**: Classes can't be tested in isolation
- **Poor Maintainability**: Changes in one class can break dependent classes
- **Limited Reusability**: Tightly coupled classes are hard to reuse in different contexts
- **Increased Complexity**: As the system grows, dependencies create complex webs of interconnections

### 4. How does loose coupling improve software design?

**Answer:** Loose coupling improves software design by:
- **Increasing Flexibility**: Easy to swap implementations without changing dependent code
- **Improving Testability**: Classes can be unit tested with mocks and stubs
- **Enhancing Maintainability**: Changes are localized and don't ripple through the system
- **Boosting Reusability**: Components can be reused in different contexts
- **Reducing Complexity**: Dependencies are managed through abstractions, making the system more modular

### 5. Give an example of tight coupling and how to refactor it to loose coupling.

**Answer:** A common example is the tight coupling shown in this project. In the tight coupling version:

```java
public class Traveller {
    Bike bike = new Bike();  // Direct instantiation - tight coupling

    public void startJourney() {
        this.bike.move();
    }
}
```

To refactor to loose coupling:
1. Create an interface: `public interface Vehicle { void move(); }`
2. Make concrete classes implement the interface: `public class Bike implements Vehicle { ... }`
3. Use dependency injection: `public Traveller(Vehicle vehicle) { this.vehicle = vehicle; }`

This allows `Traveller` to work with any vehicle without knowing the specific implementation.

### 6. What design patterns help achieve loose coupling?

**Answer:** Several design patterns promote loose coupling:
- **Dependency Injection**: Injects dependencies from outside rather than creating them internally
- **Factory Pattern**: Creates objects without specifying exact classes
- **Observer Pattern**: Allows objects to communicate without tight coupling
- **Strategy Pattern**: Encapsulates algorithms in separate classes that can be swapped
- **Adapter Pattern**: Allows incompatible interfaces to work together

### 7. How does dependency injection relate to loose coupling?

**Answer:** Dependency injection is a technique that directly supports loose coupling. Instead of a class creating its own dependencies (tight coupling), dependencies are "injected" from outside, typically through constructor parameters or setter methods. This allows:
- Dependencies to be swapped easily (e.g., for testing or different environments)
- Classes to depend on abstractions rather than concretions
- Better separation of concerns and modularity

### 8. Why is loose coupling important for testing?

**Answer:** Loose coupling is crucial for testing because:
- **Isolation**: Classes can be tested independently without involving their dependencies
- **Mocking**: Dependencies can be replaced with mock objects to simulate different scenarios
- **Faster Tests**: Tests don't require setting up complex dependency chains
- **Reliability**: Tests are less brittle and don't fail due to unrelated changes
- **Coverage**: Easier to achieve high test coverage for individual components

### 9. How does loose coupling affect code maintainability?

**Answer:** Loose coupling significantly improves maintainability by:
- **Localizing Changes**: Modifications to one module don't require changes in dependent modules
- **Reducing Ripple Effects**: Bugs or changes in one part don't cascade to others
- **Easier Refactoring**: Code can be restructured with less risk
- **Better Debugging**: Issues can be isolated to specific modules
- **Incremental Development**: New features can be added without affecting existing code

### 10. Can you explain the Dependency Inversion Principle in relation to coupling?

**Answer:** The Dependency Inversion Principle (DIP) is one of the SOLID principles that directly addresses coupling. It states:
- High-level modules should not depend on low-level modules. Both should depend on abstractions.
- Abstractions should not depend on details. Details should depend on abstractions.

In terms of coupling, DIP promotes loose coupling by ensuring that classes depend on interfaces or abstract classes rather than concrete implementations. This principle is fundamental to achieving loose coupling and is exemplified in the loose coupling example where `Traveller` depends on the `Vehicle` interface, not on specific vehicle classes.

### 11. How can you measure coupling in code?

**Answer:** Coupling can be measured using various metrics:
- **Afferent Coupling (Ca)**: Number of classes that depend on a given class
- **Efferent Coupling (Ce)**: Number of classes that a given class depends on
- **Instability (I)**: Ce / (Ce + Ca) - measures how likely a class is to change
- **Abstractness (A)**: Ratio of abstract classes/interfaces to total classes
- **Distance from Main Sequence (D)**: |A + I - 1| - indicates design quality

### 12. What are some real-world examples of tight coupling that should be avoided?

**Answer:** Common real-world examples include:
- **Direct Database Access**: Classes directly instantiating database connections
- **Hardcoded Dependencies**: Classes with new() calls to concrete classes
- **Static Method Calls**: Using static methods that create tight dependencies
- **Global State**: Classes depending on global variables or singletons
- **Inheritance Abuse**: Deep inheritance hierarchies that create tight coupling

### 13. How does loose coupling relate to microservices architecture?

**Answer:** Loose coupling is fundamental to microservices:
- **Service Independence**: Services communicate through well-defined APIs (interfaces)
- **Technology Agnostic**: Services can be implemented in different technologies
- **Independent Deployment**: Changes to one service don't require redeploying others
- **Fault Isolation**: Failures in one service don't cascade to others
- **Scalability**: Services can be scaled independently

### 14. What are the trade-offs of loose coupling?

**Answer:** While loose coupling has many benefits, there are trade-offs:
- **Increased Complexity**: More interfaces and abstractions can make code harder to understand initially
- **Performance Overhead**: Indirection through interfaces may have slight performance costs
- **Development Time**: Designing abstractions takes more upfront time
- **Learning Curve**: Developers need to understand dependency injection and related patterns
- **Over-engineering Risk**: Not all code needs loose coupling; sometimes tight coupling is acceptable for simple cases

### 15. How can you refactor existing tightly coupled code to loose coupling?

**Answer:** Steps to refactor tightly coupled code:
1. **Identify Dependencies**: Find direct instantiations and concrete references
2. **Create Abstractions**: Extract interfaces or abstract classes
3. **Implement Interfaces**: Make concrete classes implement the abstractions
4. **Introduce Dependency Injection**: Change constructors to accept dependencies
5. **Update Client Code**: Modify code that creates objects to use dependency injection
6. **Test Incrementally**: Ensure each change doesn't break functionality
7. **Remove Old Code**: Gradually remove direct instantiations


## Inversion of Control (IoC)

### What is IoC?

Inversion of Control (IoC) is a design principle in software engineering where the control of object creation and dependency management is inverted from the application code to an external container or framework. Instead of classes creating their own dependencies, an IoC container manages the lifecycle and injection of dependencies.

IoC is implemented through Dependency Injection (DI), where dependencies are "injected" into classes rather than being created internally. This promotes loose coupling, better testability, and more maintainable code.

In Spring Framework, IoC is achieved through:
- **Annotation-based Configuration**: Using annotations like `@Component`, `@Autowired`, `@Qualifier`
- **Java-based Configuration**: Using `@Configuration` and `@Bean` annotations
- **XML-based Configuration**: Using XML configuration files (less common in modern Spring)

### Annotation-based Configuration

Annotation-based configuration uses Spring's stereotype annotations to automatically detect and wire beans. The IoC container scans for annotated classes and manages their dependencies.

#### Code Example

**AppConfig.java**
```java
package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mahesh.ioc.annotationbasedcofiguration")
public class AppConfig {
}
```

**Vehicle.java**
```java
package com.mahesh.ioc.annotationbasedcofiguration;

public interface Vehicle {
    void move();
}
```

**Bike.java**
```java
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
```

**Car.java**
```java
package com.mahesh.ioc.annotationbasedcofiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

    @java.lang.Override
    @Autowired
    public void move() {
        System.out.println("Car is moving");
    }
}
```

**Cycle.java**
```java
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
```

**Traveller.java**
```java
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
```

**Client.java**
```java
package com.mahesh.ioc.annotationbasedcofiguration;

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
```

#### Explanation

In annotation-based configuration:
- `@Configuration` marks the class as a source of bean definitions
- `@ComponentScan` tells Spring to scan for components in the specified package
- `@Component` marks classes as Spring-managed beans
- `@Autowired` injects dependencies automatically
- `@Qualifier` specifies which bean to inject when multiple implementations exist

The Spring IoC container automatically creates and wires all the beans based on the annotations.

### Java-based Configuration

Java-based configuration uses `@Configuration` and `@Bean` annotations to explicitly define beans in Java code, providing more control over bean creation.

#### Code Example

**AppConfig.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Vehicle car(){
        return new Car();
    }

    @Bean
    public Vehicle bike(){
        return new Bike();
    }

    @Bean
    public Vehicle cycle(){
        return new Cycle();
    }

    @Bean
    public Traveller traveller(){
        return new Traveller(bike());
    }

}
```

**Vehicle.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

public interface Vehicle {
    void move();
}
```

**Bike.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

public class Bike implements Vehicle {
    @java.lang.Override
    public void move() {
        System.out.println("Bike is moving");
    }
}
```

**Car.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

public class Car implements Vehicle {

    @java.lang.Override
    public void move() {
        System.out.println("Car is moving");
    }
}
```

**Cycle.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

public class Cycle implements Vehicle {

    @java.lang.Override
    public void move() {
        System.out.println("Cycle is moving");
    }
}
```

**Traveller.java**
```java
package com.mahesh.ioc.javabasedconfiguration;

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
```

#### Explanation

In Java-based configuration:
- `@Configuration` marks the class as a configuration class
- `@Bean` methods define Spring beans explicitly
- The configuration class has full control over how beans are created and wired
- Dependencies are injected by calling other `@Bean` methods within the same configuration class

### Interview Questions on IoC and Configuration

Here are some common interview questions related to Inversion of Control and Spring configuration:

#### 1. What is Inversion of Control (IoC)?

**Answer:** Inversion of Control is a design principle where the control of object creation and dependency management is inverted from the application code to an external container. Instead of classes creating their own dependencies, an IoC container manages the lifecycle and injection of dependencies. This promotes loose coupling, better testability, and maintainable code.

#### 2. What is the difference between IoC and Dependency Injection?

**Answer:** IoC is a broader principle, while Dependency Injection (DI) is a specific implementation of IoC. DI is the technique where dependencies are injected into classes rather than being created internally. IoC containers use DI to manage object lifecycles and wiring.

#### 3. Explain the different types of dependency injection in Spring.

**Answer:** Spring supports three types of dependency injection:
- **Constructor Injection**: Dependencies are injected through constructor parameters
- **Setter Injection**: Dependencies are injected through setter methods
- **Field Injection**: Dependencies are injected directly into fields using @Autowired

Constructor injection is generally preferred as it ensures dependencies are set at object creation time.

#### 4. What is the difference between @Component, @Service, @Repository, and @Controller?

**Answer:** These are all stereotype annotations that mark classes as Spring-managed beans:
- `@Component`: Generic stereotype for any Spring-managed component
- `@Service`: Indicates a service layer component (business logic)
- `@Repository`: Indicates a data access component (DAO layer)
- `@Controller`: Indicates a web controller component

All of these are scanned by `@ComponentScan` and are essentially `@Component` with additional semantic meaning.

#### 5. Explain @Autowired annotation.

**Answer:** `@Autowired` is used to automatically inject dependencies. It can be used on:
- Constructor
- Setter method
- Field

Spring's IoC container resolves and injects the matching bean. If multiple beans of the same type exist, `@Qualifier` can be used to specify which bean to inject.

#### 6. What is @Qualifier annotation used for?

**Answer:** `@Qualifier` is used to resolve ambiguity when multiple beans of the same type exist in the Spring context. It allows you to specify exactly which bean should be injected by providing a qualifier name that matches the bean name or a custom qualifier.

#### 7. Explain the difference between @Bean and @Component.

**Answer:**
- `@Component` is used with annotation-based configuration and is scanned automatically
- `@Bean` is used with Java-based configuration and explicitly defines a bean in a `@Configuration` class
- `@Component` is applied to classes, while `@Bean` is applied to methods
- `@Bean` gives more control over bean creation and initialization

#### 8. What is @Configuration annotation?

**Answer:** `@Configuration` indicates that a class declares one or more `@Bean` methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime.

#### 9. Explain @ComponentScan.

**Answer:** `@ComponentScan` tells Spring where to look for annotated components. It scans the specified packages and their sub-packages for classes annotated with `@Component`, `@Service`, `@Repository`, `@Controller`, etc., and registers them as Spring beans.

#### 10. What are the advantages of IoC?

**Answer:** IoC provides several advantages:
- **Loose Coupling**: Classes don't create their own dependencies
- **Testability**: Easy to inject mock dependencies for testing
- **Maintainability**: Changes to dependencies don't affect dependent classes
- **Flexibility**: Easy to swap implementations
- **Centralized Configuration**: All bean configurations are managed in one place

#### 11. How does Spring IoC container work?

**Answer:** The Spring IoC container:
1. Reads configuration metadata (annotations, XML, or Java config)
2. Creates and manages bean instances
3. Wires dependencies between beans
4. Manages the complete lifecycle of beans (creation, initialization, destruction)
5. Provides services like dependency injection, AOP, etc.

#### 12. What is the difference between BeanFactory and ApplicationContext?

**Answer:**
- **BeanFactory**: Basic IoC container that provides basic dependency injection
- **ApplicationContext**: Advanced container that extends BeanFactory with additional features like:
  - Internationalization support
  - Event propagation
  - Resource loading
  - Automatic registration of BeanPostProcessor and BeanFactoryPostProcessor

ApplicationContext is preferred for most applications.

#### 13. Explain bean scopes in Spring.

**Answer:** Spring supports several bean scopes:
- **Singleton**: One instance per Spring container (default)
- **Prototype**: New instance each time the bean is requested
- **Request**: One instance per HTTP request (web applications)
- **Session**: One instance per HTTP session (web applications)
- **Global Session**: One instance per global HTTP session (portlets)

#### 14. What is @Scope annotation?

**Answer:** `@Scope` is used to define the scope of a Spring bean. It can be applied to classes (with `@Component`) or methods (with `@Bean`). Common values are "singleton", "prototype", "request", "session".

#### 15. How do you handle circular dependencies in Spring?

**Answer:** Spring can handle circular dependencies through setter injection, but not with constructor injection. For constructor injection, circular dependencies will cause a BeanCurrentlyInCreationException. Solutions include:
- Use setter injection instead of constructor injection
- Refactor the code to avoid circular dependencies
- Use @Lazy annotation to break the cycle

## Stereotype Annotations

Stereotype annotations in Spring are a set of annotations that mark classes as candidates for auto-detection by Spring's component scanning mechanism. They are used to define beans in the Spring IoC container without explicitly configuring them in XML or Java configuration classes. The main stereotype annotations are `@Component`, `@Service`, `@Repository`, and `@Controller`.

### Purpose of Stereotype Annotations

Stereotype annotations simplify the configuration of Spring beans by allowing the framework to automatically detect and register beans based on classpath scanning. This reduces the need for manual bean definitions and promotes cleaner, more maintainable code. They also provide semantic meaning to the beans, indicating their role in the application architecture.

### Key Stereotype Annotations

- **@Component**: A generic stereotype annotation for any Spring-managed component. It marks a class as a bean candidate for auto-detection.
- **@Service**: Indicates that the class holds business logic. It's a specialization of `@Component` for service layer classes.
- **@Repository**: Used for DAO (Data Access Object) classes that handle data access logic. It also enables Spring's exception translation for persistence exceptions.
- **@Controller**: Marks a class as a web controller in Spring MVC applications. It's a specialization of `@Component` for presentation layer classes.

### How Component Scanning Works

Component scanning is enabled using the `@ComponentScan` annotation in a configuration class. This annotation tells Spring to scan specified packages for classes annotated with stereotype annotations and register them as beans.

### Code Example

**AppConfig.java**
```java
package com.mahesh.steriotypeannotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.mahesh.steriotypeannotations")
public class AppConfig {
    
}
```

**DemoController.java**
```java
package com.mahesh.steriotypeannotations.controller;

import org.springframework.stereotype.Controller;

@Controller
public class DemoController {

    public String hello(){
        return "Hello Controller";
    }
}
```

**DemoRepository.java**
```java
package com.mahesh.steriotypeannotations.repository;

import org.springframework.stereotype.Repository;

@Repository
public class DemoRepository {

    public String hello(){
        return "Hello Repository";
    }
}
```

**DemoService.java**
```java
package com.mahesh.steriotypeannotations.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String hello(){
        return "Hello Service";
    }
}
```

**DemoClient.java**
```java
package com.mahesh.steriotypeannotations;

import com.mahesh.steriotypeannotations.controller.DemoController;
import com.mahesh.steriotypeannotations.repository.DemoRepository;
import com.mahesh.steriotypeannotations.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DemoClient {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        DemoController demoController = applicationContext.getBean(DemoController.class);
        System.out.println(demoController.hello());
        DemoRepository demoRepository = applicationContext.getBean(DemoRepository.class);
        System.out.println(demoRepository.hello());
        DemoService demoService = applicationContext.getBean(DemoService.class);
        System.out.println(demoService.hello());
    }
}
```

### Explanation

In this example:
- `AppConfig.java` uses `@ComponentScan` to scan the `com.mahesh.steriotypeannotations` package for annotated classes.
- `DemoController`, `DemoRepository`, and `DemoService` are annotated with their respective stereotype annotations.
- Spring automatically creates beans for these classes and manages their lifecycle.
- `DemoClient` retrieves these beans from the application context and calls their methods.

### Interview Questions on Stereotype Annotations

#### 1. What are stereotype annotations in Spring?

**Answer:** Stereotype annotations are a set of annotations (`@Component`, `@Service`, `@Repository`, `@Controller`) that mark classes as candidates for auto-detection by Spring's component scanning. They simplify bean configuration by allowing automatic registration of beans without explicit XML or Java configuration.

#### 2. What is the difference between @Component, @Service, @Repository, and @Controller?

**Answer:**
- `@Component`: Generic annotation for any Spring-managed component.
- `@Service`: Specialization of `@Component` for service layer classes, indicating business logic.
- `@Repository`: Used for DAO classes, enables exception translation for persistence exceptions.
- `@Controller`: Specialization of `@Component` for web controllers in Spring MVC.

#### 3. How does @ComponentScan work?

**Answer:** `@ComponentScan` enables component scanning in specified packages. Spring scans these packages for classes annotated with stereotype annotations and automatically registers them as beans in the IoC container.

#### 4. What are the benefits of using stereotype annotations?

**Answer:**
- Reduces boilerplate code by eliminating manual bean definitions.
- Provides semantic meaning to classes based on their roles.
- Enables automatic bean discovery and registration.
- Improves code maintainability and readability.

#### 5. Can you use multiple stereotype annotations on the same class?

**Answer:** Technically yes, but it's not recommended as it can be confusing. Each stereotype annotation serves a specific purpose, and using multiple can lead to ambiguity about the class's role.

#### 6. What happens if you don't use @ComponentScan?

**Answer:** Without `@ComponentScan`, Spring won't automatically detect and register beans annotated with stereotype annotations. You'd need to manually define these beans in XML or Java configuration classes.

#### 7. How do stereotype annotations relate to the @Bean annotation?

**Answer:** Stereotype annotations enable automatic bean discovery, while `@Bean` is used for explicit bean definition in Java configuration classes. Stereotype annotations are for convention-over-configuration, while `@Bean` provides more control over bean creation.

#### 8. What is the default bean name when using stereotype annotations?

**Answer:** The default bean name is the class name with the first letter in lowercase (e.g., `demoController` for `DemoController` class). You can override this using the `value` attribute of the annotation.

#### 9. Can stereotype annotations be used with XML configuration?

**Answer:** Yes, stereotype annotations work with XML configuration as long as `<context:component-scan>` is used in the XML file to enable component scanning.

#### 10. What is the role of @Repository in exception translation?

**Answer:** `@Repository` enables Spring's exception translation mechanism, which converts technology-specific exceptions (like JDBC's SQLException) into Spring's DataAccessException hierarchy, making exception handling more consistent across different persistence technologies.


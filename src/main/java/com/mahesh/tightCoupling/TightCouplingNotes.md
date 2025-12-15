# Tight Coupling in Software Design

## What is Tight Coupling?

Tight coupling refers to a situation in software design where classes or modules are highly dependent on each other. In the example provided, the `Traveller` class is tightly coupled to the `Bike` class because it directly instantiates a `Bike` object within its constructor and calls methods on it.

### Example Code Analysis

In `Traveller.java`:

```java
public class Traveller {
    Bike bike = null;

    public Traveller(){
        this.bike = new Bike();
    }

    public void startJourney(){
        this.bike.move();
    }
}
```

Here, `Traveller` is directly creating an instance of `Bike`. This means that `Traveller` cannot work without `Bike`, and any changes to `Bike` (like renaming methods or changing behavior) would require changes to `Traveller`.

## Why is Tight Coupling Not Better?

Tight coupling has several disadvantages that make it less desirable in software design:

1. **Rigidity**: Changes in one class can break other classes that depend on it. For example, if we want `Traveller` to use a `Car` instead of a `Bike`, we would need to modify the `Traveller` class directly.

2. **Difficulty in Testing**: Tightly coupled classes are hard to unit test in isolation. To test `Traveller`, you also need to deal with `Bike`, which might have its own dependencies.

3. **Poor Maintainability**: As the codebase grows, tight coupling leads to a web of dependencies that make the code fragile and hard to maintain.

4. **Limited Reusability**: Classes that are tightly coupled are less reusable in different contexts.

5. **Increased Complexity**: Understanding and modifying the code becomes more complex due to the interdependencies.

## Comparison with Loose Coupling

In contrast, loose coupling allows classes to interact through interfaces or abstractions, making the system more flexible. For instance, `Traveller` could depend on a `Vehicle` interface, allowing it to work with any vehicle implementation (Bike, Car, etc.) without knowing the specific details.

This approach promotes better design principles like the Dependency Inversion Principle and makes the code more modular and easier to extend.

## Conclusion

While tight coupling might seem simpler for small examples, it leads to problems in larger, more complex systems. Adopting loose coupling practices helps create more robust, maintainable, and scalable software.

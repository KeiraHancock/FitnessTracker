# Fitness Tracker (Java + Maven + JUnit 5)

A small OOP fitness tracker:
- Log workouts (type, minutes, calories, date)
- Track progress against a weekly calorie goal
- Unit tests with JUnit 5
- GitHub Actions CI

## Run locally
```bash
mvn -q test
# optional CLI demo
mvn -q -DskipTests package
java -cp target/fitness-tracker-1.0.0.jar com.example.fitness.Main
```

## Project structure
src/main/java/com/example/fitness/Workout.java
src/main/java/com/example/fitness/FitnessTracker.java
src/main/java/com/example/fitness/Main.java (optional)
src/test/java/com/example/fitness/FitnessTrackerTest.java
.github/workflows/maven.yml
pom.xml

## Clean Code Examples
- Immutability: Workout is immutable; prevents accidental mutation.
- Validation: Constructors & setters validate inputs (fail fast).
- Single Responsibility: FitnessTracker handles domain logic; Workout is a value object.

## CLI usage example

```text
$ java -cp target/fitness-tracker-1.0.0.jar com.example.fitness.Main
Fitness Tracker (type 'q' to quit)
Add workout (type minutes calories), or 'q': 30 250
Logged workout: 30 min, 250 cal
Add workout (type minutes calories), or 'q': 45 320
Logged workout: 45 min, 320 cal
Add workout (type minutes calories), or 'q': q
Goodbye!
```

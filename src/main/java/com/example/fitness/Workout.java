package com.example.fitness;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Immutable value object that represents one workout entry.
 */
public final class Workout {
    private final String type;     // e.g., "Run", "Swim", "Lift"
    private final int minutes;     // must be > 0
    private final int calories;    // must be >= 0
    private final LocalDate date;  // not null

    public Workout(String type, int minutes, int calories, LocalDate date) {
        if (type == null || type.isBlank()) {
            throw new IllegalArgumentException("type is required");
        }
        if (minutes <= 0) {
            throw new IllegalArgumentException("minutes must be > 0");
        }
        if (calories < 0) {
            throw new IllegalArgumentException("calories must be >= 0");
        }
        if (date == null) {
            throw new IllegalArgumentException("date is required");
        }
        this.type = type.trim();
        this.minutes = minutes;
        this.calories = calories;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "%s: %d min, %d cal on %s".formatted(type, minutes, calories, date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return minutes == workout.minutes &&
               calories == workout.calories &&
               type.equals(workout.type) &&
               date.equals(workout.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, minutes, calories, date);
    }
}

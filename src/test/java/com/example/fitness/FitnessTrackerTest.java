package com.example.fitness;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTrackerTest {

    @Test
    void addWorkout_andTotals_work() {
        FitnessTracker tracker = new FitnessTracker();
        LocalDate today = LocalDate.now();

        tracker.addWorkout(new Workout("Run", 30, 300, today));
        tracker.addWorkout(new Workout("Swim", 45, 350, today));

        assertEquals(2, tracker.getWorkouts().size(), "should store two workouts");
        assertEquals(650, tracker.totalCaloriesThisWeek(today), "sum of calories this week");
    }

    @Test
    void invalidWorkout_throws() {
        LocalDate today = LocalDate.now();

        assertThrows(IllegalArgumentException.class,
                () -> new Workout("", 30, 200, today),
                "blank type should throw");

        assertThrows(IllegalArgumentException.class,
                () -> new Workout("Run", 0, 200, today),
                "minutes <= 0 should throw");

        assertThrows(IllegalArgumentException.class,
                () -> new Workout("Run", 30, -5, today),
                "negative calories should throw");

        assertThrows(IllegalArgumentException.class,
                () -> new Workout("Run", 30, 200, null),
                "null date should throw");
    }

    @Test
    void goalProgress_andMet_flags() {
        FitnessTracker tracker = new FitnessTracker();
        LocalDate today = LocalDate.now();

        tracker.setWeeklyCaloriesGoal(1000);
        tracker.addWorkout(new Workout("Run", 40, 400, today));
        tracker.addWorkout(new Workout("Bike", 50, 300, today));

        assertFalse(tracker.isWeeklyGoalMet(today), "700/1000 not met yet");
        assertEquals(0.7, tracker.progressToWeeklyGoal(today), 1e-9);

        tracker.addWorkout(new Workout("Row", 30, 350, today));
        assertTrue(tracker.isWeeklyGoalMet(today), "1050/1000 should be met");
        assertTrue(tracker.progressToWeeklyGoal(today) >= 1.0);
    }
}

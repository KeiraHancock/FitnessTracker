package com.example.fitness;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Core domain/service class.
 * - Logs workouts
 * - Calculates totals
 * - Tracks weekly calorie goal
 */
public class FitnessTracker {

    private final List<Workout> workouts = new ArrayList<>();
    private int weeklyCaloriesGoal = 0; // 0 = no goal

    public void addWorkout(Workout workout) {
        if (workout == null) throw new IllegalArgumentException("workout is null");
        workouts.add(workout);
    }

    public List<Workout> getWorkouts() {
        return Collections.unmodifiableList(workouts);
    }

    public void setWeeklyCaloriesGoal(int calories) {
        if (calories < 0) throw new IllegalArgumentException("goal must be >= 0");
        this.weeklyCaloriesGoal = calories;
    }

    public int getWeeklyCaloriesGoal() {
        return weeklyCaloriesGoal;
    }

    /** Total calories in the current ISO week of 'today'. */
    public int totalCaloriesThisWeek(LocalDate today) {
        if (today == null) today = LocalDate.now();
        WeekFields wf = WeekFields.of(Locale.getDefault());
        int week = today.get(wf.weekOfWeekBasedYear());
        int year = today.get(wf.weekBasedYear());

        return workouts.stream()
                .filter(w -> {
                    int wWeek = w.getDate().get(wf.weekOfWeekBasedYear());
                    int wYear = w.getDate().get(wf.weekBasedYear());
                    return wWeek == week && wYear == year;
                })
                .mapToInt(Workout::getCalories)
                .sum();
    }

    /** Progress (0.0..1.0+) toward the weekly calorie goal. If no goal set, returns 0. */
    public double progressToWeeklyGoal(LocalDate today) {
        if (weeklyCaloriesGoal <= 0) return 0.0;
        double progressed = totalCaloriesThisWeek(today);
        return progressed / weeklyCaloriesGoal;
    }

    /** True if weekly goal met or exceeded. */
    public boolean isWeeklyGoalMet(LocalDate today) {
        if (weeklyCaloriesGoal <= 0) return false;
        return totalCaloriesThisWeek(today) >= weeklyCaloriesGoal;
    }
}

package com.example.fitness;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Fitness Tracker (type 'q' to quit)");
        while (true) {
            System.out.print("Add workout (type minutes calories), or 'q': ");
            String line = sc.nextLine().trim();

            if (line.equalsIgnoreCase("q")) {
                System.out.println("Goodbye!");
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length != 2) {
                System.out.println("Please enter exactly TWO numbers like: 30 250");
                continue;
            }

            try {
                int minutes = Integer.parseInt(parts[0]);
                int calories = Integer.parseInt(parts[1]);

                // TODO: adjust these calls if your FitnessTracker API is different
                // Example if you have an immutable Workout:
                // FitnessTracker tracker = SingletonHolder.INSTANCE; // however you're holding it
                // tracker.logWorkout(new Workout("General", minutes, calories, java.time.LocalDate.now()));
                // For a simple demo without needing global state:
                System.out.println("Logged workout: " + minutes + " min, " + calories + " cal");
            } catch (NumberFormatException e) {
                System.out.println("Invalid numbers. Try something like: 45 320");
            }
        }
    }
}

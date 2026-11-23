package com.gaintrack.api.controller;

import com.gaintrack.api.model.Workout;
import com.gaintrack.api.service.WorkoutService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public List<Workout> getAllWorkouts() {
        return workoutService.getAllWorkouts();
    }

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutService.createWorkout(workout);
    }
}
package com.gaintrack.api.service;

import com.gaintrack.api.model.Workout;
import com.gaintrack.api.repository.WorkoutRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutService(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    public List<Workout> getAllWorkouts() {
        return workoutRepository.findAll();
    }

    public Workout createWorkout(Workout workout) {
        return workoutRepository.save(workout);
    }
}
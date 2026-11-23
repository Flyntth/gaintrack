package com.gaintrack.api.service;

import com.gaintrack.api.exception.ResourceNotFoundException;
import com.gaintrack.api.model.Exercise;
import com.gaintrack.api.model.PerformedSet;
import com.gaintrack.api.model.Workout;
import com.gaintrack.api.repository.ExerciseRepository;
import com.gaintrack.api.repository.PerformedSetRepository;
import com.gaintrack.api.repository.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformedSetService {
    private final PerformedSetRepository performedSetRepository;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public PerformedSetService(PerformedSetRepository performedSetRepository,  ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        this.performedSetRepository = performedSetRepository;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    public List<PerformedSet> getAllPerformedSets() {
        return performedSetRepository.findAll();
    }

    public PerformedSet createPerformedSet(PerformedSet performedSet) {
        return performedSetRepository.save(performedSet);
    }

    public PerformedSet createPerformedSetFromIds(Long workoutId, Long exerciseId, Double weight, Integer reps) {
        Workout workout = workoutRepository.findById(workoutId).orElseThrow(() -> new ResourceNotFoundException("Workout with id: " + workoutId + " not found"));

        Exercise exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new ResourceNotFoundException("Exercise with id: " + exerciseId + " not found"));

        PerformedSet set = new PerformedSet();
        set.setWeight(weight);
        set.setReps(reps);
        set.setWorkout(workout);
        set.setExercise(exercise);

        return performedSetRepository.save(set);
    }
}

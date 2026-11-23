package com.gaintrack.api.service;

import com.gaintrack.api.model.Exercise;
import com.gaintrack.api.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    public Exercise createExercise(Exercise exercise){
        return exerciseRepository.save(exercise);
    }

    public Exercise updateExercise(Long id, Exercise exerciseDetails){
        Exercise exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exercise Not Found"));

        exercise.setName(exerciseDetails.getName());
        exercise.setMuscleGroup(exerciseDetails.getMuscleGroup());

        return exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id){
        exerciseRepository.deleteById(id);
    }
}

package com.gaintrack.api.controller;

import com.gaintrack.api.model.Exercise;
import com.gaintrack.api.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController (ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public Exercise createExercise(@RequestBody Exercise exercise){
        return exerciseService.createExercise(exercise);
    }

    @PutMapping("/{id}")
    public Exercise updateExercise(@PathVariable Long id, @RequestBody Exercise exercise){
        return exerciseService.updateExercise(id, exercise);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable Long id){
        exerciseService.deleteExercise(id);
    }
}

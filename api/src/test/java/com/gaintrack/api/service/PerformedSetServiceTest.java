package com.gaintrack.api.service;

import com.gaintrack.api.model.Exercise;
import com.gaintrack.api.model.PerformedSet;
import com.gaintrack.api.model.Workout;
import com.gaintrack.api.repository.ExerciseRepository;
import com.gaintrack.api.repository.PerformedSetRepository;
import com.gaintrack.api.repository.WorkoutRepository;
import com.gaintrack.api.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PerformedSetServiceTest {

    @Mock
    private PerformedSetRepository performedSetRepository;

    @Mock
    private WorkoutRepository workoutRepository;

    @Mock
    private ExerciseRepository exerciseRepository;

    @InjectMocks
    private PerformedSetService performedSetService;

    @Test
    void getAllPerformedSets_shouldReturnAllSets() {
        // ===== ARRANGE : on prépare les données et le comportement du mock =====
        PerformedSet set1 = new PerformedSet();
        set1.setWeight(80.0);
        set1.setReps(10);

        PerformedSet set2 = new PerformedSet();
        set2.setWeight(100.0);
        set2.setReps(5);

        List<PerformedSet> fakeSets = List.of(set1, set2);

        // On dit au mock : "quand on appellera findAll(), renvoie fakeSets"
        when(performedSetRepository.findAll()).thenReturn(fakeSets);

        // ===== ACT : on appelle la méthode à tester =====
        List<PerformedSet> result = performedSetService.getAllPerformedSets();

        // ===== ASSERT : on vérifie que le résultat est correct =====
        assertEquals(2, result.size());          // il doit y avoir 2 sets
        assertEquals(80.0, result.get(0).getWeight());  // le 1er a bien un poids de 80
    }

    @Test
    void createPerformedSet_shouldCreatePerformedSet_whenIdsExist() {
        // ===== ARRANGE =====
        Workout workout = new Workout();
        workout.setId(1L);

        Exercise exercise = new Exercise();
        exercise.setId(1L);

        // On configure les mocks : quand on cherche ces ids, on les trouve
        when(workoutRepository.findById(1L)).thenReturn(Optional.of(workout));
        when(exerciseRepository.findById(1L)).thenReturn(Optional.of(exercise));
        // Quand on sauve, le repo renvoie le set tel quel (astuce : on renvoie l'argument reçu)
        when(performedSetRepository.save(any(PerformedSet.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // ===== ACT =====
        PerformedSet result = performedSetService.createPerformedSetFromIds(1L, 1L, 80.0, 10);

        // ===== ASSERT =====
        assertEquals(80.0, result.getWeight());
        assertEquals(10, result.getReps());
        assertEquals(workout, result.getWorkout());
        assertEquals(exercise, result.getExercise());
    }

    @Test
    void createPerformedSetFromIds_shouldThrowException_whenWorkoutNotFound() {
        when(workoutRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            performedSetService.createPerformedSetFromIds(1L, 1L, 80.0, 10);
        });
    }
}
package com.gaintrack.api.controller;

import com.gaintrack.api.dto.CreatePerformSetRequest;
import com.gaintrack.api.model.PerformedSet;
import com.gaintrack.api.service.PerformedSetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/performedSets")
public class PerformedSetController {
    private final PerformedSetService performedSetService;

    public PerformedSetController(PerformedSetService performedSetService) {
        this.performedSetService = performedSetService;
    }

    @GetMapping
    public List<PerformedSet> getAllPerformedSets () { return performedSetService.getAllPerformedSets(); }

    @PostMapping
    public PerformedSet createPerformedSet (@RequestBody PerformedSet performedSet) {
        return performedSetService.createPerformedSet(performedSet);
    }

    @PostMapping("/from-ids")
    public PerformedSet createPerformedSetFromIds (@Valid @RequestBody CreatePerformSetRequest request) {
        return performedSetService.createPerformedSetFromIds(request.workoutId(), request.exerciseId(), request.weight(), request.reps());
    }
}

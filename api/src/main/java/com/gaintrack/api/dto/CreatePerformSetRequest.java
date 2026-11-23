package com.gaintrack.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreatePerformSetRequest(@NotNull Long workoutId, @NotNull Long exerciseId, @NotNull @Positive Double weight,@NotNull @Positive Integer reps) {
}

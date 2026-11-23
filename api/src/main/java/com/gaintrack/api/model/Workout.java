package com.gaintrack.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    /*
    @OneToMany(mappedBy = "workout")
    @JsonManagedReference
    private List<Exercise> exercises;
    */

    @OneToMany(mappedBy = "workout")
    @JsonManagedReference("workout-sets")
    private List<PerformedSet> performedSets;
}
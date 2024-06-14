package org.example.timetable_generator.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String className;

    @OneToMany(mappedBy = "classDetails", cascade = CascadeType.ALL)
    private List<DivisionDetails> divisions = new ArrayList<>();

}

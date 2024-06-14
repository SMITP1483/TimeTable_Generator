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
public class DivisionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String divisionName;

    public DivisionDetails(String divisionName) {
        this.divisionName = divisionName;
    }

    @ManyToOne
    @JoinColumn(name = "ClassDetails_id")
    private ClassDetails classDetails;


    @OneToMany(mappedBy = "divisionDetails", cascade = CascadeType.ALL)
    private List<TeacherDetails> teacherDetailsList = new ArrayList<>();
}

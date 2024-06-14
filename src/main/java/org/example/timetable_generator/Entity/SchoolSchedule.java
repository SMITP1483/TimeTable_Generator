package org.example.timetable_generator.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String Day;
    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp recessTime;
    private Timestamp breakTime;
    private Timestamp lectureDuration;


}

package org.example.timetable_generator.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachingAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String subject;

    public TeachingAssignment(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "TeachingAssignment{" +
                "id=" + id +
                ", subject='" + subject + '\'' +
                '}';
    }

    @ManyToOne
    @JoinColumn(name = "TeacherDetails_id")
    private TeacherDetails teacherDetails;
}

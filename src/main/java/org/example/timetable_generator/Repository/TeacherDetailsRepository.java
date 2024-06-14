package org.example.timetable_generator.Repository;

import org.example.timetable_generator.Entity.DivisionDetails;
import org.example.timetable_generator.Entity.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherDetailsRepository extends JpaRepository<TeacherDetails, Long> {

    TeacherDetails save(TeacherDetails teacherDetails);




}

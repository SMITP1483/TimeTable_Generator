package org.example.timetable_generator.Repository;

import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Entity.TeachingAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeachingAssignmentRepository extends JpaRepository<TeachingAssignment, Long> {

    TeachingAssignment save(TeachingAssignment teachingAssignment);

    List<TeachingAssignment> findAll();

    List<TeachingAssignment> findByTeacherDetails(TeacherDetails teacherDetails);

//    TeachingAssignment findByTeacherDetails(List<TeacherDetails> teacherDetails);
}

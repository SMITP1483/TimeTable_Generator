package org.example.timetable_generator.Service;

import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Entity.TeachingAssignment;
import org.example.timetable_generator.Repository.TeachingAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachingAssignmentService {

    @Autowired
    TeachingAssignmentRepository teachingAssignmentRepository;

    public List<TeachingAssignment> findByTeacherDetails(TeacherDetails teacherDetails) {
        try {
            return teachingAssignmentRepository.findByTeacherDetails(teacherDetails);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TeachingAssignment saveTeachingAssignment(TeachingAssignment teachingAssignmentList) {
        try {
            return teachingAssignmentRepository.save(teachingAssignmentList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<TeachingAssignment> getTeachingAssignment() {
        try {
            return teachingAssignmentRepository.findAll();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

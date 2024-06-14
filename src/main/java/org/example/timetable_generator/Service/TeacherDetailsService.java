package org.example.timetable_generator.Service;

import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Repository.TeacherDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherDetailsService {

    @Autowired
    private TeacherDetailsRepository teacherDetailsRepository;

    public TeacherDetails saveTeacherDetails(TeacherDetails teacherDetails) {
        try {
            return teacherDetailsRepository.save(teacherDetails);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public List<TeacherDetails> getAllTeacherDetailsByDivisionId(String  divisionId) {
//        try {
//            return teacherDetailsRepository.findTeacherDetailsByDivisionsName(divisionId);
//        }catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}

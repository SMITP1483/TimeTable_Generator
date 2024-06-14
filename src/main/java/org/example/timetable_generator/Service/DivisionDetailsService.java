package org.example.timetable_generator.Service;

import org.example.timetable_generator.Entity.DivisionDetails;
import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Repository.DivisionDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DivisionDetailsService {
    @Autowired
    private DivisionDetailsRepository divisionDetailsRepository;

    public List<TeacherDetails> getTeacherDetailsByDivisionName(String divisionName){
        try {
            return divisionDetailsRepository.findTeacherDetailsByDivisionName(divisionName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public DivisionDetails saveDivisionDetails(DivisionDetails divisionDetails) {
        try {
            return divisionDetailsRepository.save(divisionDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DivisionDetails> getAllDivisionDetails() {
        try {
          return divisionDetailsRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}

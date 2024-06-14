package org.example.timetable_generator.Service;

import org.example.timetable_generator.Entity.ClassDetails;
import org.example.timetable_generator.Repository.ClassDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassDetailsService {

    @Autowired
    private ClassDetailsRepository classDetailsRepository;

    public ClassDetails saveClassDetails(ClassDetails classDetails) {
        try{
            return classDetailsRepository.save(classDetails);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

package org.example.timetable_generator.Repository;

import org.example.timetable_generator.Entity.ClassDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDetailsRepository extends JpaRepository<ClassDetails, Long> {

    ClassDetails save(ClassDetails classDetails);


}

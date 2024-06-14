package org.example.timetable_generator.Repository;

import org.example.timetable_generator.Entity.DivisionDetails;
import org.example.timetable_generator.Entity.TeacherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionDetailsRepository extends JpaRepository<DivisionDetails, Long> {

    DivisionDetails save(DivisionDetails divisionDetails);

    List<DivisionDetails> findAll();

    @Query("SELECT d.teacherDetailsList FROM DivisionDetails d WHERE d.divisionName = :divisionName")
    List<TeacherDetails> findTeacherDetailsByDivisionName(@Param("divisionName") String divisionName);

}

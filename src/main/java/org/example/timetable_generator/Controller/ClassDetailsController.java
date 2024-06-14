package org.example.timetable_generator.Controller;


import jakarta.servlet.http.HttpServletRequest;
import org.example.timetable_generator.Entity.ClassDetails;
import org.example.timetable_generator.Entity.DivisionDetails;
import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Entity.TeachingAssignment;
import org.example.timetable_generator.Service.ClassDetailsService;
import org.example.timetable_generator.Service.DivisionDetailsService;
import org.example.timetable_generator.Service.TeacherDetailsService;
import org.example.timetable_generator.Service.TeachingAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClassDetailsController {


    @Autowired
    private ClassDetailsService classDetailsService;

    @Autowired
    private TeacherDetailsService teacherDetailsService;

    @Autowired
    private DivisionDetailsService divisionDetailsService;

    @Autowired
    private TeachingAssignmentService teachingAssignmentService;


    @PostMapping("/classDetails")
    public String saveClassDetails(Model model, HttpServletRequest request) {
        try {
            int noOfClasses = Integer.parseInt(request.getParameter("NoOfClasses"));

            for (int i = 0; i < noOfClasses; i++) {
                String className = request.getParameter("ClassName_" + (i + 1));

                ClassDetails classDetails = new ClassDetails();
                classDetails.setClassName(className);

                List<DivisionDetails> divisionDetailsList = new ArrayList<>();
                int noOfDivisions = Integer.parseInt(request.getParameter("NoOfDivisions_" + (i + 1)));

                for (int j = 0; j < noOfDivisions; j++) {
                    String divisionName = request.getParameter("DivisionName_" + (i + 1) + "_" + (j + 1));
                    DivisionDetails divisionDetails = new DivisionDetails();
                    divisionDetails.setDivisionName(divisionName);
                    divisionDetails.setClassDetails(classDetails); // Set reference to ClassDetails

                    List<TeacherDetails> teacherDetailsList = new ArrayList<>();
                    int noOfTeachers = Integer.parseInt(request.getParameter("NoOfTeachers_" + (i + 1) + "_" + (j + 1)));

                    for (int k = 0; k < noOfTeachers; k++) {
                        String firstName = request.getParameter("firstName_" + (i + 1) + "_" + (j + 1) + "_" + (k + 1));
                        String lastName = request.getParameter("lastName_" + (i + 1) + "_" + (j + 1) + "_" + (k + 1));
                        String email = request.getParameter("email_" + (i + 1) + "_" + (j + 1) + "_" + (k + 1));

                        TeacherDetails teacherDetails = new TeacherDetails(firstName, lastName, email);
                        teacherDetailsList.add(teacherDetails); // Add to division's teacher list
                        teacherDetails.setDivisionDetails(divisionDetails);

                        int noOfSubjects = Integer.parseInt(request.getParameter("NoOfSubjects_" + (i + 1) + "_" + (j + 1) + "_" + (k + 1)));

                        for (int l = 0; l < noOfSubjects; l++) {
                            String subjectName = request.getParameter("subject_" + (i + 1) + "_" + (j + 1) + "_" + (k + 1) + "_" + (l + 1));
                            TeachingAssignment teachingAssignment = new TeachingAssignment(subjectName);
                            teachingAssignment.setTeacherDetails(teacherDetails);
                            teacherDetails.getTeachingAssignments().add(teachingAssignment); // Add subject to teacher
                        }
                    }
                    divisionDetails.setTeacherDetailsList(teacherDetailsList); // Set teacher list for division
                    divisionDetailsList.add(divisionDetails);
                }
                classDetails.setDivisions(divisionDetailsList);
                classDetailsService.saveClassDetails(classDetails); // Save ClassDetails with relationships
            }
            model.addAttribute("successMessage", "Your Data submitted successfully");
            return "ClassDetails";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while processing your request");
            return "ClassDetails";
        }
    }
}
package org.example.timetable_generator.Controller;

import org.example.timetable_generator.Entity.DivisionDetails;
import org.example.timetable_generator.Entity.TeacherDetails;
import org.example.timetable_generator.Service.DivisionDetailsService;
import org.example.timetable_generator.Service.TeachingAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class TimeTableController {

    @Autowired
    private DivisionDetailsService divisionDetailsService;

    @Autowired
    private TeachingAssignmentService teachingAssignmentService;

    Map<Integer, List<List<String>>> TimeTableOfOneClass = new HashMap<>();

    Map<TeacherDetails, List<TimeSlot>> timeTable = new HashMap<>();

    @PostMapping("/pdfGeneration")
    public String pdfGeneration(Model model) {
        try {
            //for one class and it's all division

            List<DivisionDetails> divisionDetailsList = divisionDetailsService.getAllDivisionDetails();

            List<List<TeacherDetails>> ListOfTeacherDetails = new ArrayList<>();

            for (DivisionDetails divisionDetails : divisionDetailsList) {
                ListOfTeacherDetails.add(divisionDetailsService.getTeacherDetailsByDivisionName(divisionDetails.getDivisionName()));
            }


            for (int k = 0; k < ListOfTeacherDetails.size() - 1; k++) {
                List<TeacherDetails> teacherDetailsList = ListOfTeacherDetails.get(k);
                Collections.shuffle(teacherDetailsList);
                List<List<String>> TimeTableOfOneDivision = generateTimeTable(teacherDetailsList);
                TimeTableOfOneClass.put(k, TimeTableOfOneDivision);

            }
            model.addAttribute("TimeTableOfOneClass", TimeTableOfOneClass);
            return "TimeTableGenerator";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "An error occurred while processing your request");
            return "TimeTableGenerator";
        }
    }

    public List<List<String>> generateTimeTable(List<TeacherDetails> teacherDetailsList) {

        List<List<String>> TimeTableOfOneDivision = new ArrayList<>();
        Random rand = new Random();
        List<String> timeTableOfOneDay;
        for (int i = 0; i < 5; i++) {
            Collections.shuffle(teacherDetailsList);
            timeTableOfOneDay = new ArrayList<>();
            int teacherIndex = rand.nextInt(teacherDetailsList.size());

            for (int j = 0; j < 6; j++) {
                TimeSlot timeSlot = new TimeSlot(i, j);
                TeacherDetails teacherDetails;
                if (teacherIndex < teacherDetailsList.size()) {
                    teacherDetails = teacherDetailsList.get(teacherIndex);

                    if (timeTable.get(teacherDetails) != null && timeTable.get(teacherDetails).contains(timeSlot)) {
                        teacherDetails = teacherDetailsList.get(++teacherIndex);
                    } else if (timeTable.get(teacherDetails) != null) {
                        timeTable.get(teacherDetails).add(timeSlot);
                    } else {
                        List<TimeSlot> timeSlotList = new ArrayList<>();
                        timeSlotList.add(timeSlot);
                        timeTable.put(teacherDetails, timeSlotList);
                    }


                    if (timeTableOfOneDay.contains(teacherDetails.getFirstName() + "_" + teacherDetails.getLastName() + "_" + teachingAssignmentService.findByTeacherDetails(teacherDetails).getFirst().getSubject()))
                        timeTableOfOneDay.add("Period_" + (j + 1) + " " + "No Lecture");
                    else
                        timeTableOfOneDay.add(teacherDetails.getFirstName() + "_" + teacherDetails.getLastName() + "_" + teachingAssignmentService.findByTeacherDetails(teacherDetails).getFirst().getSubject());
                } else {
                    teacherIndex = 0;
                    Collections.shuffle(teacherDetailsList);
                    teacherDetails = teacherDetailsList.get(teacherIndex);

                    if (timeTable.get(teacherDetails) != null && timeTable.get(teacherDetails).contains(timeSlot)) {

                        teacherDetails = teacherDetailsList.get(++teacherIndex);
                    } else if (timeTable.get(teacherDetails) != null) {
                        timeTable.get(teacherDetails).add(timeSlot);
                    } else {
                        List<TimeSlot> timeSlotList = new ArrayList<>();
                        timeSlotList.add(timeSlot);
                        timeTable.put(teacherDetails, timeSlotList);
                    }


                    timeTableOfOneDay.add(teacherDetails.getFirstName() + "_" + teacherDetails.getLastName() + "_" + teachingAssignmentService.findByTeacherDetails(teacherDetails).getFirst().getSubject());
                }
                teacherIndex++;
            }
            TimeTableOfOneDivision.add(timeTableOfOneDay);
        }
        return TimeTableOfOneDivision;
    }


}

class TimeSlot {
    int day;
    int hour;

    public TimeSlot(int day, int hour) {
        this.day = day;
        this.hour = hour;
    }

}

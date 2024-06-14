package org.example.timetable_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

public class TimeTableGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTableGeneratorApplication.class, args);
    }

}

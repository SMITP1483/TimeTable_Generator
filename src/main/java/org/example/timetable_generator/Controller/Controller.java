package org.example.timetable_generator.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("{fileName}")
    public String getFile(String fileName) {
        return fileName;
    }
}

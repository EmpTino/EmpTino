package EmpTino.empTino.lectureTime.controller;

import EmpTino.empTino.lectureTime.service.LectureTimeService;
import org.springframework.stereotype.Controller;

@Controller
public class LectureTimeController {
    private final LectureTimeService lectureTimeService;

    public LectureTimeController(LectureTimeService lectureTimeService){
        this.lectureTimeService = lectureTimeService;
    }
}

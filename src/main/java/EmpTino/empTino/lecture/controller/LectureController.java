package EmpTino.empTino.lecture.controller;

import EmpTino.empTino.lecture.service.LectureService;
import org.springframework.stereotype.Controller;

@Controller
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }
}

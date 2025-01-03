package EmpTino.empTino.lecture.controller;

import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lecture.service.LectureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping
public class LectureController {
    private final LectureService lectureService;

    public LectureController(LectureService lectureService){
        this.lectureService = lectureService;
    }
}

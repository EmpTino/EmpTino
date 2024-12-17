package EmpTino.empTino.classroom.controller;

import EmpTino.empTino.classroom.domain.Classroom;
import EmpTino.empTino.classroom.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/classroom")
@RequiredArgsConstructor
public class ClassroomController {

    public final ClassroomService classroomService;

    @GetMapping()
    public String classroom(Model model) {
        List<Classroom> classrooms = classroomService.findAllClassroom();
        model.addAttribute("classrooms", classrooms);

        return "classrooms";
    }

}

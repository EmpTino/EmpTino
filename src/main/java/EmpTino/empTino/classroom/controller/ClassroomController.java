package EmpTino.empTino.classroom.controller;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public String classrooms(Model model) {
        List<ClassroomDAO> classrooms = classroomService.findAllClassroom();
        model.addAttribute("classrooms", classrooms);  // JSP로 전달할 데이터 추가
        return "classrooms";  // JSP 파일 이름 (classrooms.jsp)
    }



}

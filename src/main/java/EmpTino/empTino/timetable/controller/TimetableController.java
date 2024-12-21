package EmpTino.empTino.timetable.controller;


import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.timetable.domain.TimetableDAO;
import EmpTino.empTino.timetable.repository.TimetableDAORepository;
import EmpTino.empTino.timetable.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService){
        this.timetableService = timetableService;
    }

    @GetMapping
    public String showTimetable(Model model) {
        // 요일 목록 추가
        List<String> days = List.of("월", "화", "수", "목", "금");
        model.addAttribute("days", days);

        return "timetable"; // 시간표 화면
    }

    @GetMapping("/search")
    public String searchAvailableRooms(
            @RequestParam("time") int time,
            @RequestParam("day") String day,
            Model model
    ) {
        // 조건에 맞는 빈 강의실 목록 가져오기
        List<ClassroomDAO> availableRooms = timetableService.findAvailableClassrooms(time, day);

        if (availableRooms.isEmpty()) {
            model.addAttribute("message", "빈 강의실 없음");
        } else {
            model.addAttribute("rooms", availableRooms);
        }

        model.addAttribute("selectedTime", time);
        model.addAttribute("selectedDay", day);

        return "timetable"; // 결과를 같은 화면에 표시
    }
}

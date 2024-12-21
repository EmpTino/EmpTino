package EmpTino.empTino.timetable.controller;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.timetable.domain.TimetableDAO;
import EmpTino.empTino.timetable.service.TimetableService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/timetables")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    // 현재 로그인된 사용자의 ID를 가져오는 유틸리티 메서드
    private String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // username을 userId로 사용한다고 가정
    }

    // 로그인한 사용자의 시간표 조회
    @GetMapping
    public String getTimeTableByUser(Model model) {
        String loggedInUserId = getLoggedInUserId();
        List<TimetableDAO> timetables = timetableService.getTimeTableByUserId(loggedInUserId);

        // 시간표 데이터를 모델에 추가
        model.addAttribute("timetables", timetables);

        // 요일 목록 추가
        List<String> days = List.of("월", "화", "수", "목", "금");
        model.addAttribute("days", days);

        return "timetable"; // /WEB-INF/views/timetable.jsp로 매핑
    }

    // 시간표에 강의 추가
    @PostMapping("/add")
    public ResponseEntity<TimetableDAO> addLecturesToTimeTable(
            @RequestBody List<Integer> lectureIds) {
        String loggedInUserId = getLoggedInUserId();
        TimetableDAO updatedTimetable = timetableService.addLecturesToTimeTable(loggedInUserId, lectureIds);
        return ResponseEntity.ok(updatedTimetable);
    }
}

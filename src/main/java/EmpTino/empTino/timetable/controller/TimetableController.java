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
@RequestMapping("/api/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService) {
        this.timetableService = timetableService;
    }

    private String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            return null; // 인증되지 않은 사용자
        }
        return authentication.getName();
    }

    // 시간표 조회 및 화면 표시
    @GetMapping("/page")
    public String showTimetablePage(Model model) {
        // 시간표 관련 데이터 추가 (예: 요일 데이터)
        List<String> days = List.of("월", "화", "수", "목", "금");
        model.addAttribute("days", days);

        return "timetable"; // JSP 뷰 이름 반환
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

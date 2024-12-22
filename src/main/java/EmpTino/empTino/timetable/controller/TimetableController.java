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

    // 현재 로그인된 사용자의 ID를 가져오는 유틸리티 메서드
    private String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // username을 userId로 사용
    }

    // 시간표 조회 및 화면 표시
    @GetMapping
    public Object getTimeTableOrView(@RequestParam(required = false) String format, Model model) {
        String loggedInUserId = getLoggedInUserId();

        // 시간표 데이터 가져오기
        List<TimetableDAO> timetables = timetableService.getTimeTableByUserId(loggedInUserId);

        if ("json".equalsIgnoreCase(format)) {
            // JSON 응답 반환
            return ResponseEntity.ok(timetables);
        } else {
            // 요일 목록 추가
            List<String> days = List.of("월", "화", "수", "목", "금");
            model.addAttribute("days", days);
            model.addAttribute("timetables", timetables);

            // JSP 뷰 반환
            return "timetable";
        }
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

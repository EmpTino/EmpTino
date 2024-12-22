package EmpTino.empTino.timetable.controller;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.timetable.domain.TimetableDAO;
import EmpTino.empTino.timetable.service.TimetableService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
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

    // 시간표에 강의 추가
    @PostMapping("/add")
    public ResponseEntity<TimetableDAO> addLecturesToTimeTable(
            @RequestBody List<Integer> lectureIds) {
        String loggedInUserId = getLoggedInUserId();
        TimetableDAO updatedTimetable = timetableService.addLecturesToTimeTable(loggedInUserId, lectureIds);
        return ResponseEntity.ok(updatedTimetable);
    }

/**
    // 로그인한 사용자의 시간표 조회
    @GetMapping
    public ResponseEntity<List<TimetableDAO>> getTimeTableByUser() {
        String loggedInUserId = getLoggedInUserId();
        List<TimetableDAO> timetables = timetableService.getTimeTableByUserId(loggedInUserId);
        return ResponseEntity.ok(timetables);
    }
 **/
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

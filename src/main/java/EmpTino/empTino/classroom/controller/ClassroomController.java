package EmpTino.empTino.classroom.controller;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.service.ClassroomService;
import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lecture.service.LectureService;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import EmpTino.empTino.lectureTime.service.LectureTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/api/classrooms")
@Slf4j
public class ClassroomController {
    private final ClassroomService classroomService;
    private final LectureService lectureService;
    private final LectureTimeService lectureTimeService;

    public ClassroomController(ClassroomService classroomService, LectureService lectureService, LectureTimeService lectureTimeService) {
        this.classroomService = classroomService;
        this.lectureService = lectureService;
        this.lectureTimeService = lectureTimeService;
    }

    @GetMapping("/all")
    public String findAllClassrooms(
            @RequestParam(defaultValue = "none") String sortBy, // 정렬 기준
            @RequestParam(defaultValue = "asc") String order,  // 정렬 방향
            Model model) {

        List<ClassroomDAO> classrooms = classroomService.findAllClassroom();

        log.info("Found {} classrooms [ all ]", classrooms.size());  // classroom 리스트의 크기 출력
        for (ClassroomDAO classroom : classrooms) {
            log.info("Classroom: {}", classroom);  // 각 강의실 객체 출력
        }
        // 정렬 처리
        if (!sortBy.equals("none")) {
            switch (sortBy) {
                case "classroomName":
                    classrooms.sort(order.equals("asc")
                            ? Comparator.comparing(ClassroomDAO::getClassroomName)
                            : Comparator.comparing(ClassroomDAO::getClassroomName).reversed());
                    break;
                case "buildingName":
                    classrooms.sort(order.equals("asc")
                            ? Comparator.comparing(ClassroomDAO::getBuildingName)
                            : Comparator.comparing(ClassroomDAO::getBuildingName).reversed());
                    break;
                case "floor":
                    classrooms.sort(order.equals("asc")
                            ? Comparator.comparingInt(ClassroomDAO::getFloor)
                            : Comparator.comparingInt(ClassroomDAO::getFloor).reversed());
                    break;
                default:
                    break;
            }
        }

        model.addAttribute("classrooms", classrooms);
        model.addAttribute("sortBy", sortBy); // 현재 정렬 기준 전달
        model.addAttribute("order", order);   // 현재 정렬 방향 전달

        return "classrooms"; // JSP 파일 이름
    }

    @GetMapping("/{id}")
    public String findClassroomById(@PathVariable String id, Model model) {
        ClassroomDAO classroom = classroomService.findClassroomById(id);

        List<LectureDAO> lectures = lectureService.findLecturesByClassroomId(id);
        log.info("Found {} lectures for classroom {} [ id ]", lectures.size(), classroom);
        List<LectureTimeDAO> lectureTimes = new ArrayList<>();
        for (LectureDAO lecture : lectures) {
            lectureTimes.addAll(lectureTimeService.findLectureTimeByLecture(lecture.getLectureId()));
        }
        log.info("Found {} lecturestime for classroom {} [ id ]", lectureTimes.size(), classroom);
        Map<String, Set<LectureTimeDAO>> lectureTimesMap = new HashMap<>();
        for (LectureTimeDAO lectureTime : lectureTimes) {
            log.info("Adding lecture time for lectureId {}: {} - {}",
                    lectureTime.getLectureId(), lectureTime.getDay(), lectureTime.getTime());
            lectureTimesMap
                    .computeIfAbsent(lectureTime.getLectureId(), k -> new HashSet<>())
                    .add(lectureTime);
        }

        model.addAttribute("classroom", classroom);
        model.addAttribute("lectures", lectures);
        model.addAttribute("lectureTimesMap", lectureTimesMap);

        return "classroom_detail";
    }


}

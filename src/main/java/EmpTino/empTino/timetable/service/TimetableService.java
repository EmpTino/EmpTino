package EmpTino.empTino.timetable.service;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import EmpTino.empTino.lecture.repository.LectureDAORepository;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import EmpTino.empTino.timetable.repository.TimetableDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class TimetableService {
    private final ClassroomDAORepository classroomDAORepository;
    private final LectureDAORepository lectureDAORepository;
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public TimetableService(
            ClassroomDAORepository classroomDAORepository,
            LectureDAORepository lectureDAORepository,
            LectureTimeDAORepository lectureTimeDAORepository
    ) {
        this.classroomDAORepository = classroomDAORepository;
        this.lectureDAORepository = lectureDAORepository;
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }

    public List<ClassroomDAO> findAvailableClassrooms(int time, String day) {
        // 조건에 맞는 lectureTimeId를 제외한 lectureId 목록
        List<String> busyLectureIds = lectureTimeDAORepository.findAll().stream()
                .filter(lectureTime -> lectureTime.getTime() == time
                        && lectureTime.getDay().equals(day))
                .map(lectureTime -> lectureTime.getLectureId())
                .collect(Collectors.toList());

        // busyLectureIds에 포함되지 않는 ClassroomId를 가져옴
        List<String> availableClassroomIds = lectureDAORepository.findAll().stream()
                .filter(lecture -> !busyLectureIds.contains(lecture.getLectureId()))
                .map(lecture -> lecture.getClassroomId())
                .collect(Collectors.toList());

        // 위의 ClassroomId 목록에 해당하는 빈 강의실 조회
        return classroomDAORepository.findAll().stream()
                .filter(classroom -> availableClassroomIds.contains(classroom.getClassroomId()))
                .collect(Collectors.toList());
    }
}
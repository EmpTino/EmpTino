package EmpTino.empTino.timetable.service;

import EmpTino.empTino.timetable.domain.TimetableDAO;
import EmpTino.empTino.timetable.repository.TimetableDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimetableService {

    private final TimetableDAORepository timetableDAORepository;

    public TimetableService(TimetableDAORepository timetableDAORepository) {
        this.timetableDAORepository = timetableDAORepository;
    }

    // 시간표에 강의 추가
    public TimetableDAO addLecturesToTimeTable(String loggedInUserId, List<Integer> lectureIds) {
        // 사용자의 시간표만 수정 가능
        Optional<TimetableDAO> optionalTimetable = timetableDAORepository.findByUserId(loggedInUserId).stream().findFirst();

        if (optionalTimetable.isPresent()) {
            // 기존 시간표에 강의 추가
            TimetableDAO timetable = optionalTimetable.get();
            List<Integer> currentLectures = timetable.getLectureIds();
            currentLectures.addAll(lectureIds);
            timetable.setLectureIds(currentLectures);
            return timetableDAORepository.save(timetable);
        } else {
            // 새 시간표 생성
            TimetableDAO newTimetable = TimetableDAO.builder()
                    .userId(loggedInUserId)
                    .lectureIds(lectureIds)
                    .build();
            return timetableDAORepository.save(newTimetable);
        }
    }

    // 로그인한 사용자의 시간표 조회
    public List<TimetableDAO> getTimeTableByUserId(String loggedInUserId) {
        return timetableDAORepository.findByUserId(loggedInUserId);
    }
}
package EmpTino.empTino.lecture.service;

import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lecture.repository.LectureDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureService {
    private final LectureDAORepository lectureDAORepository;

    public LectureService(LectureDAORepository lectureDAORepository) {
        this.lectureDAORepository = lectureDAORepository;
    }

    public List<LectureDAO> findLecturesByClassroomId(String ClassroomId) {
        return lectureDAORepository.findLectureDAOSByClassroomId(ClassroomId);
    }


}

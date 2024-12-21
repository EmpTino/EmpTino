package EmpTino.empTino.lectureTime.service;

import EmpTino.empTino.lectureTime.domain.LecturetimeDAO;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectureTimeService {
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public LectureTimeService(LectureTimeDAORepository lectureTimeDAORepository){
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }

    public List<LecturetimeDAO> findLectureTimeByLecture(String lectureId) {
        return lectureTimeDAORepository.findByLectureId(lectureId);
    }

}

package EmpTino.empTino.lecture.service;

import EmpTino.empTino.lecture.repository.LectureDAORepository;
import org.springframework.stereotype.Service;

@Service
public class LectureService {
    private final LectureDAORepository lectureDAORepository;

    public LectureService(LectureDAORepository lectureDAORepository){
        this.lectureDAORepository = lectureDAORepository;
    }
}

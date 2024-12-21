package EmpTino.empTino.lectureTime.service;

import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import org.springframework.stereotype.Service;

@Service
public class LectureTimeService {
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public LectureTimeService(LectureTimeDAORepository lectureTimeDAORepository){
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }
}

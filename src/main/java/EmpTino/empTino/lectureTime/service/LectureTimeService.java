package EmpTino.empTino.lectureTime.service;

import EmpTino.empTino.lecture.repository.LectureDAORepository;
import EmpTino.empTino.lecture.service.LectureService;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import ch.qos.logback.core.rolling.LengthCounter;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.inline.StandardHTMLInliner;

@Service
public class LectureTimeService {
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public LectureTimeService(LectureTimeDAORepository lectureTimeDAORepository){
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }
}

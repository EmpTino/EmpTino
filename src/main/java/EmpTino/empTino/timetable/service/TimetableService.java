package EmpTino.empTino.timetable.service;

import EmpTino.empTino.timetable.repository.TimetableDAORepository;
import org.springframework.stereotype.Service;


@Service
public class TimetableService {

    private final TimetableDAORepository timetableDAORepository;

    public TimetableService(TimetableDAORepository timetableDAORepository) {
        this.timetableDAORepository = timetableDAORepository;
    }
}
package EmpTino.empTino.timetable.controller;


import EmpTino.empTino.timetable.domain.TimetableDAO;
import EmpTino.empTino.timetable.repository.TimetableDAORepository;
import EmpTino.empTino.timetable.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(TimetableService timetableService){
        this.timetableService = timetableService;
    }

}

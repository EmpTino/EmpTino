package EmpTino.empTino.classroom.service;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    private final ClassroomDAORepository classroomDAORepository;

    public ClassroomService(ClassroomDAORepository classroomDAORepository) {
        this.classroomDAORepository = classroomDAORepository;
    }
}
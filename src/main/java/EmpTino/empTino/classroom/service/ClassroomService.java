package EmpTino.empTino.classroom.service;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassroomService {
    private final ClassroomDAORepository classroomDAORepository;

    public ClassroomService(ClassroomDAORepository classroomDAORepository) {
        this.classroomDAORepository = classroomDAORepository;
    }

    public List<ClassroomDAO> findAllClassroom() {
        return classroomDAORepository.findAll();
    }

    public ClassroomDAO findClassroomById(String id) {
        return classroomDAORepository.findById(id).orElse(null);
    }
}

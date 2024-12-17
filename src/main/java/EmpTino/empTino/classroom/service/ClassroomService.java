package EmpTino.empTino.classroom.service;

import EmpTino.empTino.classroom.domain.Classroom;
import EmpTino.empTino.classroom.repository.ClassroomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public List<Classroom> findAllClassroom(){
        return classroomRepository.findAll();
    }


}

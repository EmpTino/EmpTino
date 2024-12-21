package EmpTino.empTino.lecture.repository;

import EmpTino.empTino.lecture.domain.LectureDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureDAORepository extends JpaRepository<LectureDAO, String> {
    List<LectureDAO> findLectureDAOSByClassroomId(String classroomId);
}

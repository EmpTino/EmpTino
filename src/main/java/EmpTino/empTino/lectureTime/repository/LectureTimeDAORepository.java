package EmpTino.empTino.lectureTime.repository;

import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureTimeDAORepository extends JpaRepository<LectureTimeDAO, String> {
    List<LectureTimeDAO> findByLectureId(String lectureId);
}

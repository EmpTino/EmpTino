package EmpTino.empTino.lecture.repository;

import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LectureDAORepository extends JpaRepository<LectureDAO, UUID> {
}

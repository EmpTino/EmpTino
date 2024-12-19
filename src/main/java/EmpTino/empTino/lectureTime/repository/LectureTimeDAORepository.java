package EmpTino.empTino.lectureTime.repository;

import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LectureTimeDAORepository extends JpaRepository<LectureTimeDAO, UUID> {
}

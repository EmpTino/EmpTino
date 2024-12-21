package EmpTino.empTino.lectureTime.repository;

import EmpTino.empTino.lectureTime.domain.LecturetimeDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureTimeDAORepository extends JpaRepository<LecturetimeDAO, String> {
    List<LecturetimeDAO> findByLectureId(String lectureId);
}

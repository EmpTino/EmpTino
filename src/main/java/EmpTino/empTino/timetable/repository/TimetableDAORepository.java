package EmpTino.empTino.timetable.repository;

import EmpTino.empTino.timetable.domain.TimetableDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimetableDAORepository extends JpaRepository<TimetableDAO, String> {
    List<TimetableDAO> findByUserId(String userId); // 사용자 ID로 시간표 조회
}

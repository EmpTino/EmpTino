package EmpTino.empTino.timetable.repository;

import EmpTino.empTino.timetable.domain.TimetableDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableDAORepository extends JpaRepository<TimetableDAO, String> {
}

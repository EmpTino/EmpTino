package EmpTino.empTino.classroom.repository;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClassroomDAORepository extends JpaRepository<ClassroomDAO, UUID> {

}

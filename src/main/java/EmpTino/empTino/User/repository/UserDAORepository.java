package EmpTino.empTino.User.repository;

import EmpTino.empTino.User.domain.UserDAO;
import EmpTino.empTino.review.domain.ReviewDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserDAORepository extends JpaRepository<UserDAO, UUID> {
}

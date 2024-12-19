package EmpTino.empTino.review.repository;

import EmpTino.empTino.review.domain.ReviewDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewDAORepository extends JpaRepository<ReviewDAO, UUID> {
}

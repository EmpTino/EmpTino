package EmpTino.empTino.friend.repository;

import EmpTino.empTino.friend.domain.FriendDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FriendDAORepository extends JpaRepository<FriendDAO, UUID> {
}

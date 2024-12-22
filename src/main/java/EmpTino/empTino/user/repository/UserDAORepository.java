package EmpTino.empTino.user.repository;

import EmpTino.empTino.user.domain.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDAORepository extends JpaRepository<UserDAO, String> {
    Optional<UserDAO> findByUserName(String userName);

    Optional<UserDAO> findByUserNameAndPassword(String userName, String password);

    boolean existsByUserName(String userName);
}

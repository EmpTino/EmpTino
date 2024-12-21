package EmpTino.empTino.friend.repository;

import EmpTino.empTino.friend.domain.FriendDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendDAORepository extends JpaRepository<FriendDAO, String> {
    // 요청을 보낸 친구 조회
    List<FriendDAO> findByFromUserId(String fromUserId);

    // 친구 요청 조회
    List<FriendDAO> findByToUserIdAndIsAccepted(String toUserId, boolean isAccepted);

    // 친구 목록 조회
    List<FriendDAO> findByFromUserIdOrToUserIdAndIsAccepted(String fromUserId, String toUserId, boolean isAccepted);

    // JPQL 사용
    @Query("SELECT f FROM FriendDAO f WHERE (f.fromUserId = :userId OR f.toUserId = :userId) AND f.isAccepted = :isAccepted")
    List<FriendDAO> findFriendsByUserIdAndIsAccepted(@Param("userId") String userId, @Param("isAccepted") boolean isAccepted);

}

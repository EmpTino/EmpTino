package EmpTino.empTino.friend.repository;

import EmpTino.empTino.friend.domain.FriendDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FriendDAORepository extends JpaRepository<FriendDAO, String> {
    // 특정 관계가 이미 존재하는지 확인
    @Query("SELECT f FROM FriendDAO f WHERE (f.fromUserId = :fromUserId AND f.toUserId = :toUserId) " +
            "OR (f.fromUserId = :toUserId AND f.toUserId = :fromUserId)")
    Optional<FriendDAO> findFriendRelationship(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    // 친구 요청 목록 조회
    @Query("SELECT f FROM FriendDAO f WHERE f.toUserId = :toUserId AND f.isAccepted = false")
    List<FriendDAO> findPendingRequests(@Param("toUserId") String toUserId);

    // 친구 목록 조회
    @Query("SELECT f FROM FriendDAO f WHERE (f.fromUserId = :userId OR f.toUserId = :userId) AND f.isAccepted = true")
    List<FriendDAO> findAcceptedFriends(@Param("userId") String userId);
}

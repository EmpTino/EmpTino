package EmpTino.empTino.friend.service;

import EmpTino.empTino.friend.domain.FriendDAO;
import EmpTino.empTino.friend.repository.FriendDAORepository;
import EmpTino.empTino.user.repository.UserDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private final FriendDAORepository friendDAORepository;
    private final UserDAORepository userDAORepository;

    public FriendService(FriendDAORepository friendDAORepository, UserDAORepository userDAORepository) {
        this.friendDAORepository = friendDAORepository;
        this.userDAORepository = userDAORepository;
    }

    // 친구 요청 보내기
    public FriendDAO sendFriendRequest(String fromUserName, String toUserName) {
        // userName을 통해 userId 조회
        String fromUserId = userDAORepository.findByUserName(fromUserName)
                .orElseThrow(() -> new IllegalArgumentException("Sender user not found")).getUserId();
        String toUserId = userDAORepository.findByUserName(toUserName)
                .orElseThrow(() -> new IllegalArgumentException("Receiver user not found")).getUserId();

        // 중복 요청 확인
        Optional<FriendDAO> existingRelationship = friendDAORepository.findFriendRelationship(fromUserId, toUserId);
        if (existingRelationship.isPresent()) {
            throw new IllegalStateException("Friend request already exists or relationship already established.");
        }

        // 친구 요청 생성
        FriendDAO friend = FriendDAO.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .isAccepted(false)
                .build();

        return friendDAORepository.save(friend);
    }

    // 친구 요청 수락
    public FriendDAO acceptFriendRequest(String friendId) {
        FriendDAO friend = friendDAORepository.findById(friendId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found"));
        friend.setAccepted(true);
        return friendDAORepository.save(friend);
    }

    // 친구 요청 거절 또는 삭제
    public void deleteFriendRequest(String friendId) {
        friendDAORepository.deleteById(friendId);
    }

    // 친구 요청 목록 조회
    public List<FriendDAO> getPendingRequests(String toUserName) {
        String toUserId = userDAORepository.findByUserName(toUserName)
                .orElseThrow(() -> new IllegalArgumentException("User not found")).getUserId();
        return friendDAORepository.findPendingRequests(toUserId);
    }

    // 친구 목록 조회
    public List<FriendDAO> getFriends(String userName) {
        String userId = userDAORepository.findByUserName(userName)
                .orElseThrow(() -> new IllegalArgumentException("User not found")).getUserId();
        return friendDAORepository.findAcceptedFriends(userId);
    }
}

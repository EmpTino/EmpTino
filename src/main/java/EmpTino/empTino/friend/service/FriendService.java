package EmpTino.empTino.friend.service;

import EmpTino.empTino.friend.domain.FriendDAO;
import EmpTino.empTino.friend.repository.FriendDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {
    private final FriendDAORepository friendDAORepository;

    public FriendService(FriendDAORepository friendDAORepository) {
        this.friendDAORepository = friendDAORepository;
    }

    // 친구 요청 보내기
    public FriendDAO sendFriendRequest(String fromUserId, String toUserId) {
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
    public List<FriendDAO> getPendingRequests(String toUserId) {
        return friendDAORepository.findPendingRequests(toUserId); // 대기 중인 요청 목록
    }

    // 친구 목록 조회
    public List<FriendDAO> getFriends(String userId) {
        return friendDAORepository.findAcceptedFriends(userId); // 수락된 친구 목록
    }
}

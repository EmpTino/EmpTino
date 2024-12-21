package EmpTino.empTino.friend.controller;

import EmpTino.empTino.friend.domain.FriendDAO;
import EmpTino.empTino.friend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // 친구 요청 보내기
    @PostMapping("/request")
    public ResponseEntity<FriendDAO> sendFriendRequest(@RequestParam String fromUserId, @RequestParam String toUserId) {
        return ResponseEntity.ok(friendService.sendFriendRequest(fromUserId, toUserId));
    }

    // 친구 요청 수락
    @PostMapping("/accept/{friendId}")
    public ResponseEntity<FriendDAO> acceptFriendRequest(@PathVariable String friendId) {
        return ResponseEntity.ok(friendService.acceptFriendRequest(friendId));
    }

    // 친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> deleteFriendRequest(@PathVariable String friendId) {
        friendService.deleteFriendRequest(friendId);
        return ResponseEntity.noContent().build();
    }

    // 친구 요청 조회
    @GetMapping("/requests")
    public ResponseEntity<List<FriendDAO>> getPendingRequests(@RequestParam String toUserId) {
        return ResponseEntity.ok(friendService.getPendingRequests(toUserId));
    }

    // 친구 목록 조회
    @GetMapping
    public ResponseEntity<List<FriendDAO>> getFriends(@RequestParam String userId) {
        return ResponseEntity.ok(friendService.getFriends(userId));
    }
}

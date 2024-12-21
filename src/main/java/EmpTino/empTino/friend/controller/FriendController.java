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

    @PostMapping("/request")
    public ResponseEntity<FriendDAO> sendFriendRequest(
            @RequestParam String fromUserName,
            @RequestParam String toUserName) {
        return ResponseEntity.ok(friendService.sendFriendRequest(fromUserName, toUserName));
    }

    // 친구 요청 수락
    @PostMapping("/accept/{friendId}")
    public ResponseEntity<FriendDAO> acceptFriendRequest(@PathVariable String friendId) {
        return ResponseEntity.ok(friendService.acceptFriendRequest(friendId));
    }

    // 친구 요청 조회
    @GetMapping("/requests")
    public ResponseEntity<List<FriendDAO>> getPendingRequests(@RequestParam String toUserName) {
        return ResponseEntity.ok(friendService.getPendingRequests(toUserName));
    }

    // 친구 목록 조회
    @GetMapping
    public ResponseEntity<List<FriendDAO>> getFriends(@RequestParam String userName) {
        return ResponseEntity.ok(friendService.getFriends(userName));
    }
}

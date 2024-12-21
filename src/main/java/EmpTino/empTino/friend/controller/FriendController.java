package EmpTino.empTino.friend.controller;

import EmpTino.empTino.friend.domain.FriendDAO;
import EmpTino.empTino.friend.service.FriendService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/friends")
public class FriendController {

    private final FriendService friendService;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    // 현재 로그인된 사용자의 ID를 가져오는 유틸리티 메서드
    private String getLoggedInUserName(Authentication authentication) {
        return authentication.getName();
    }

    // 친구 목록 페이지로 연결
    @GetMapping("/list")
    public String getFriendsPage(Authentication authentication, Model model) {
        String userName = getLoggedInUserName(authentication);
        List<FriendDAO> friends = friendService.getFriends(userName);
        model.addAttribute("friends", friends);
        return "friend"; // /WEB-INF/views/friend.jsp로 매핑
    }
    // 친구 요청 목록 페이지로 연결
    @GetMapping("/request")
    public String getFriendRequestsPage(Authentication authentication, Model model) {
        String userName = getLoggedInUserName(authentication);
        List<FriendDAO> pendingRequests = friendService.getPendingRequests(userName);
        model.addAttribute("requests", pendingRequests);
        return "friend_request"; // /WEB-INF/views/friend_request.jsp로 매핑
    }
    
    // 친구 요청 보내기
    @PostMapping("/request")
    @ResponseBody
    public ResponseEntity<FriendDAO> sendFriendRequest(
            @RequestParam String fromUserName,
            @RequestParam String toUserName) {
        return ResponseEntity.ok(friendService.sendFriendRequest(fromUserName, toUserName));
    }

    // 친구 요청 수락
    @PostMapping("/accept/{friendId}")
    @ResponseBody
    public ResponseEntity<FriendDAO> acceptFriendRequest(@PathVariable String friendId) {
        return ResponseEntity.ok(friendService.acceptFriendRequest(friendId));
    }

    // 친구 요청 조회
    @GetMapping("/requests")
    @ResponseBody
    public ResponseEntity<List<FriendDAO>> getPendingRequests(@RequestParam String toUserName) {
        return ResponseEntity.ok(friendService.getPendingRequests(toUserName));
    }

    // 친구 목록 조회
    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<List<FriendDAO>> getFriends(@RequestParam String userName) {
        return ResponseEntity.ok(friendService.getFriends(userName));
    }
}

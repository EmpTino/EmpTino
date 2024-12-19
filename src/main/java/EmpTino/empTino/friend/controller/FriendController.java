package EmpTino.empTino.friend.controller;

import EmpTino.empTino.friend.service.FriendService;
import org.springframework.stereotype.Controller;

@Controller
public class FriendController {
    private final FriendService friendService;

    public FriendController(FriendService friendService){
        this.friendService = friendService;
    }
}

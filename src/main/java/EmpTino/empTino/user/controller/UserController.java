package EmpTino.empTino.user.controller;

import EmpTino.empTino.user.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
}

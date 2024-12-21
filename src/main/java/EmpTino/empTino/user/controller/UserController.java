package EmpTino.empTino.user.controller;

import EmpTino.empTino.user.domain.UserDAO;
import EmpTino.empTino.user.security.JwtUtil;
import EmpTino.empTino.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDAO userDAO) {
        try {
            UserDAO registeredUser = userService.register(userDAO);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String userName, @RequestParam String password) {
        try {
            UserDAO loggedInUser = userService.login(userName, password);
            String token = jwtUtil.generateToken(loggedInUser.getUserName());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}

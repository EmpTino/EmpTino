package EmpTino.empTino.user.controller;

import EmpTino.empTino.user.domain.UserDAO;
import EmpTino.empTino.user.security.JwtUtil;
import EmpTino.empTino.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "signup";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestParam String realName,
            Model model) {
        try {
            UserDAO user = new UserDAO();
            user.setUserName(userName);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setRealName(realName);

            userService.signup(user);
            model.addAttribute("success", "회원가입이 완료되었습니다.");
            return "redirect:/api/auth/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signup";
        }
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String userName,
            @RequestParam String password,
            Model model) {
        try {
            UserDAO user = userService.login(userName, password);
            model.addAttribute("message", user.getNickname() + "님 환영합니다.");
            return "redirect:/"; // 성공 시 메인 화면으로 리다이렉트
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/"; // 실패 시 로그인 화면으로 다시 이동
        }
    }
}

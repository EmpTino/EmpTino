package EmpTino.empTino.login;

import EmpTino.empTino.login.LoginService;
import EmpTino.empTino.user.domain.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
        UserDAO user = loginService.login(userName, password);
        if (user != null) {
            model.addAttribute("message", user.getNickname() + "님 환영합니다");
            return "login_success";
        } else {
            model.addAttribute("error", "ID 또는 Password가 잘못되었습니다.");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(
            @RequestParam String userName,
            @RequestParam String password,
            @RequestParam String nickname,
            @RequestParam String realName,
            Model model) {

        if (loginService.isUserNameTaken(userName)) {
            model.addAttribute("error", "이미 존재하는 ID입니다.");
            return "signup";
        }

        loginService.signup(userName, password, nickname, realName);
        model.addAttribute("success", "회원가입이 완료되었습니다.");
        return "redirect:/login";
    }
}
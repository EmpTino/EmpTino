package EmpTino.empTino.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String Main() {
        return "main";
    }

    @GetMapping("/main/login")
    public String login() {
        return "login";
    }

    @GetMapping("/main/search")
    public String search() {
        return "search";
    }

    @GetMapping("/main/timetable")
    public String timetable() {
        return "timetable";
    }
}
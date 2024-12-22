package EmpTino.empTino.search;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import EmpTino.empTino.search.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public String searchPage() {
        return "search"; // search.jsp 화면 호출
    }

    @GetMapping("/find")
    public String searchEmptyRooms(@RequestParam String building, @RequestParam int time, Model model) {
        List<ClassroomDAO> emptyClassrooms = searchService.findEmptyClassrooms(building, time);

        if (emptyClassrooms.isEmpty()) {
            model.addAttribute("message", "빈 강의실이 없습니다.");
        } else {
            model.addAttribute("emptyClassrooms", emptyClassrooms);
        }

        return "search"; // 결과를 포함한 search.jsp 화면 호출
    }
}
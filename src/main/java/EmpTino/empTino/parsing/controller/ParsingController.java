package EmpTino.empTino.parsing.controller;

import EmpTino.empTino.parsing.service.ParsingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/parsing")
public class ParsingController {

    private final ParsingService parsingService;

    public ParsingController(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    // 엑셀 파일 업로드 페이지로 이동
    @RequestMapping("/upload")
    public String showUploadPage() {
        return "uploadExcel"; // JSP 파일 이름
    }

    // 엑셀 파일 처리 로직
    @PostMapping("/process")
    public String processExcelFile(MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                model.addAttribute("message", "파일이 비어 있습니다. 올바른 파일을 업로드하세요.");
                return "uploadExcel";
            }
            parsingService.parseAndSave(file); // Service 호출
            model.addAttribute("message", "파일이 성공적으로 처리되었습니다!");
        } catch (Exception e) {
            model.addAttribute("message", "오류가 발생했습니다: " + e.getMessage());
        }
        return "uploadExcel"; // 결과 메시지를 같은 페이지에 출력
    }
}

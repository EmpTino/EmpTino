package EmpTino.empTino.parsing.controller;
import EmpTino.empTino.parsing.service.ExcelParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/excel")
public class ParsingController {

    private final ExcelParserService excelParserService;

    public ParsingController(ExcelParserService excelParserService) {
        this.excelParserService = excelParserService;
    }

    /**
     * 엑셀 업로드 페이지로 이동
     */
    @GetMapping("/upload")
    public String uploadPage() {
        return "upload"; // JSP 파일 이름 (upload.jsp)
    }

    /**
     * 엑셀 파일 업로드 및 처리
     * @param file 업로드된 엑셀 파일
     * @return 처리 결과 메시지를 포함한 페이지
     * @throws IOException 파일 처리 중 오류
     */
    @PostMapping("/upload")
    public ModelAndView uploadExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView modelAndView = new ModelAndView("upload");

        try {
            // 파일을 임시 디렉토리에 저장
            File tempFile = File.createTempFile("lecture_data", ".xlsx");
            file.transferTo(tempFile);

            // 엑셀 파일 처리
            excelParserService.processExcelFile(tempFile.getAbsolutePath());

            // 임시 파일 삭제
            tempFile.delete();

            // 성공 메시지 전달
            modelAndView.addObject("message", "엑셀 데이터가 성공적으로 처리되었습니다!");
        } catch (Exception e) {
            // 실패 메시지 전달
            modelAndView.addObject("message", "엑셀 처리 중 오류가 발생했습니다: " + e.getMessage());
        }

        return modelAndView;
    }
}

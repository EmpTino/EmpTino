package EmpTino.empTino.parsing.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelParserService {
    private final ParsingService parsingService;

    ExcelParserService(ParsingService parsingService) {
        this.parsingService = parsingService;
    }

    /**
     * 엑셀 파일을 처리하는 메서드
     * @param //filePath 엑셀 파일 경로
     * @throws //IOException 파일 읽기 오류
     */
    public void processExcelFile(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 읽기

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                // 첫 번째 행은 헤더이므로 건너뜀
                continue;
            }

            // 엑셀 데이터 읽기
            String lectureName = getCellValue(row.getCell(3)); // D열: 강의 이름
            String professor = getCellValue(row.getCell(12)); // M열: 교수 이름
            String lectureTimeString = getCellValue(row.getCell(14)); // O열: 강의 시간 및 강의실 정보

            // 데이터 저장
            parsingService.saveLectureData(lectureName, professor, lectureTimeString);
        }

        workbook.close();
        fis.close();
    }

    /**
     * 셀의 값을 문자열로 반환
     * @param cell 셀 객체
     * @return 셀의 값 (문자열)
     */
    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        }
        return "";
    }
}

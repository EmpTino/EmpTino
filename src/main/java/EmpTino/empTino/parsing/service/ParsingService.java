package EmpTino.empTino.parsing.service;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lecture.repository.LectureDAORepository;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParsingService {

    // 레포지토리 주입
    private final ClassroomDAORepository classroomDAORepository;
    private final LectureDAORepository lectureDAORepository;
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public ParsingService(ClassroomDAORepository classroomDAORepository,
                       LectureDAORepository lectureDAORepository,
                       LectureTimeDAORepository lectureTimeDAORepository) {
        this.classroomDAORepository = classroomDAORepository;
        this.lectureDAORepository = lectureDAORepository;
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }

    // 엑셀 파일을 파싱하고 데이터를 저장하는 메서드
    public void parseAndSave(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream(); // 업로드된 파일의 InputStream 가져오기
        Workbook workbook = new XSSFWorkbook(inputStream); // 엑셀 파일 읽기
        Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트 선택

        // 각 행을 순회하며 데이터 파싱 및 저장
        for (int i = 1; i <= sheet.getLastRowNum(); i++) { // 첫 번째 행(헤더)은 건너뜀
            Row row = sheet.getRow(i);

            // 셀 값 가져오기
            String lectureName = getCellValue(row.getCell(0)); // 교과목명
            String professor = getCellValue(row.getCell(1)); // 교수명
            String lectureTimeStr = getCellValue(row.getCell(2)); // 강의 시간
            String classroomStr = getCellValue(row.getCell(3)); // 강의실

            // 강의실 데이터 파싱
            ClassroomDAO classroomDAO = parseClassroom(classroomStr);
            classroomDAO = saveOrGetClassroom(classroomDAO); // 강의실 정보를 저장하거나 기존 정보 가져오기

            // 강의 데이터 생성 및 저장
            LectureDAO lectureDAO = LectureDAO.builder()
                    .LectureName(lectureName)
                    .professor(professor)
                    .ClassroomId(classroomDAO.getClassroomId()) // 강의실 ID 연결
                    .build();
            lectureDAO = lectureDAORepository.save(lectureDAO);

            // 강의 시간 데이터 파싱 및 저장
            List<LectureTimeDAO> lectureTimes = parseLectureTimes(lectureDAO.getLectureId(), lectureTimeStr);
            lectureTimeDAORepository.saveAll(lectureTimes);
        }

        workbook.close(); // 작업 완료 후 리소스 해제
    }

    // 셀 값을 String으로 변환하는 유틸리티 메서드
    private String getCellValue(Cell cell) {
        if (cell == null) return ""; // 빈 셀 처리
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : String.valueOf(cell.getNumericCellValue());
    }

    // 강의실 데이터를 파싱하는 메서드
    private ClassroomDAO parseClassroom(String classroomStr) {
        // 강의실 포맷: [건물 이름][층][강의실 번호] 예: G406
        Pattern pattern = Pattern.compile("([A-Za-z가-힣]+)(\\d)(\\d+)");
        Matcher matcher = pattern.matcher(classroomStr);

        if (matcher.find()) {
            String buildingName = matcher.group(1); // 건물 이름
            int floor = Integer.parseInt(matcher.group(2)); // 층
            String classroomName = matcher.group(3); // 강의실 번호

            // 강의실 데이터 생성
            return ClassroomDAO.builder()
                    .buildingName(buildingName)
                    .floor(floor)
                    .classroomName(classroomName)
                    .build();
        }
        throw new IllegalArgumentException("잘못된 강의실 포맷: " + classroomStr);
    }

    // 강의실 정보를 저장하거나 기존 정보를 가져오는 메서드
    private ClassroomDAO saveOrGetClassroom(ClassroomDAO classroomDAO) {
        return classroomDAORepository.findByBuildingNameAndClassroomName(
                classroomDAO.getBuildingName(), classroomDAO.getClassroomName()
        ).orElseGet(() -> classroomDAORepository.save(classroomDAO)); // 기존 데이터가 없으면 새로 저장
    }

    // 강의 시간 데이터를 파싱하는 메서드
    private List<LectureTimeDAO> parseLectureTimes(String lectureId, String lectureTimeStr) {
        List<LectureTimeDAO> lectureTimes = new ArrayList<>();

        // 강의 시간 포맷: [요일][시간][세부시간](강의실) 예: 화 [6~7]14:30~16:20(G406)
        Pattern pattern = Pattern.compile("([월화수목금토일])\\s*\\[([\\d~]+)]([\\d:~]+)\\(([A-Za-z가-힣0-9]+)\\)");
        Matcher matcher = pattern.matcher(lectureTimeStr);

        while (matcher.find()) {
            String day = matcher.group(1); // 요일
            String timeRange = matcher.group(2); // 시간 범위

            // 시간 범위 파싱
            if (timeRange.contains("~")) {
                String[] parts = timeRange.split("~");
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);

                // 범위 내 각각의 시간 저장
                for (int i = start; i <= end; i++) {
                    LectureTimeDAO timeDAO = LectureTimeDAO.builder()
                            .lectureId(lectureId)
                            .day(day)
                            .time(Integer.parseInt(String.valueOf(i))) // 시간 저장
                            .build();
                    lectureTimes.add(timeDAO);
                }
            } else {
                // 단일 시간 저장
                LectureTimeDAO timeDAO = LectureTimeDAO.builder()
                        .lectureId(lectureId)
                        .day(day)
                        .time(Integer.parseInt(timeRange))
                        .build();
                lectureTimes.add(timeDAO);
            }
        }

        return lectureTimes;
    }
}

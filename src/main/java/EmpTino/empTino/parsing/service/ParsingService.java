package EmpTino.empTino.parsing.service;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import EmpTino.empTino.lecture.domain.LectureDAO;
import EmpTino.empTino.lecture.repository.LectureDAORepository;
import EmpTino.empTino.lectureTime.domain.LectureTimeDAO;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import EmpTino.empTino.parsing.domain.LectureParser;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParsingService {

    @Autowired
    private ClassroomDAORepository classroomDAORepository;

    @Autowired
    private LectureDAORepository lectureDAORepository;

    @Autowired
    private LectureTimeDAORepository lectureTimeDAORepository;

    @Transactional
    public void saveLectureData(String lectureName, String professor, String lectureTimeString) {
        // 0. 빈 셀 통과
        if (lectureName == null || lectureName.trim().isEmpty() ||
                professor == null || professor.trim().isEmpty() ||
                lectureTimeString == null || lectureTimeString.trim().isEmpty()) {
            return;
        }

        // 1. 강의실 정보 추출 및 파싱
        List<LectureParser.ClassroomInfo> classroomInfos = parseClassroomInfos(lectureTimeString);

        // 강의실 정보가 없는 경우 경고 메시지 출력 및 처리 중단
        if (classroomInfos.isEmpty()) {
            System.err.println("강의실 정보가 유효하지 않습니다: " + lectureTimeString);
            return; // 강의실 정보가 없는 데이터는 무시
        }

        // 첫 번째 강의실 선택
        ClassroomDAO firstClassroom = null;
        for (int i = 0; i < classroomInfos.size(); i++) {
            LectureParser.ClassroomInfo classroomInfo = classroomInfos.get(i);
            ClassroomDAO classroom = findOrCreateClassroom(classroomInfo);

            if (i == 0) {
                firstClassroom = classroom; // 첫 번째 강의실로 설정
            }
        }

        // 강의실이 없으면 처리 중단
        if (firstClassroom == null) {
            throw new IllegalArgumentException("강의실 정보가 유효하지 않습니다: " + lectureTimeString);
        }

        // 2. 강의 시간 파싱
        List<LectureParser.LectureTime> lectureTimes = LectureParser.parseLectureTimes(lectureTimeString);

        // 3. 강의 저장
        LectureDAO lecture = new LectureDAO();
        lecture.setLectureName(lectureName);
        lecture.setProfessor(professor);
        lecture.setClassroomId(firstClassroom.getClassroomId()); // 첫 번째 강의실 ID 저장
        lecture = lectureDAORepository.save(lecture);

        // 4. 강의 시간 저장
        for (LectureParser.LectureTime lectureTime : lectureTimes) {
            for (int time : lectureTime.getTimes()) {
                LectureTimeDAO lectureTimeDAO = new LectureTimeDAO();
                lectureTimeDAO.setLectureId(lecture.getLectureId());
                lectureTimeDAO.setDay(lectureTime.getDay());
                lectureTimeDAO.setTime(time);
                lectureTimeDAORepository.save(lectureTimeDAO);
            }
        }
    }

    private List<LectureParser.ClassroomInfo> parseClassroomInfos(String lectureTimeString) {
        List<LectureParser.ClassroomInfo> classroomInfos = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\(([^\\)]+)\\)");
        Matcher matcher = pattern.matcher(lectureTimeString);

        if (matcher.find()) {
            String[] classroomParts = matcher.group(1).split(",");
            for (String classroomPart : classroomParts) {
                LectureParser.ClassroomInfo classroomInfo = parseClassroomInfo(classroomPart.trim());
                if (classroomInfo != null) {
                    classroomInfos.add(classroomInfo);
                }
            }
        }

        return classroomInfos;
    }

    private LectureParser.ClassroomInfo parseClassroomInfo(String classroomString) {
        if (classroomString == null || classroomString.trim().isEmpty()) {
            return null;
        }

        Pattern pattern = Pattern.compile("([가-힣A-Z]+)동?(\\d+)(호)?");
        Matcher matcher = pattern.matcher(classroomString);

        if (matcher.find()) {
            String buildingName = matcher.group(1); // 건물명 추출
            int floor = Integer.parseInt(matcher.group(2).substring(0, 1)); // 층 정보 추출
            String roomNumber = matcher.group(2); // 호수 정보 추출
            return new LectureParser.ClassroomInfo(buildingName, floor, roomNumber);
        }

        return null;
    }

    private ClassroomDAO findOrCreateClassroom(LectureParser.ClassroomInfo classroomInfo) {
        if (classroomInfo == null) return null;

        ClassroomDAO existingClassroom = classroomDAORepository.findByBuildingNameAndFloorAndClassroomName(
                classroomInfo.getBuildingName(),
                classroomInfo.getFloor(),
                classroomInfo.getRoomNumber()
        );

        if (existingClassroom != null) {
            return existingClassroom;
        }

        ClassroomDAO newClassroom = new ClassroomDAO();
        newClassroom.setBuildingName(classroomInfo.getBuildingName());
        newClassroom.setFloor(classroomInfo.getFloor());
        newClassroom.setClassroomName(classroomInfo.getRoomNumber());
        return classroomDAORepository.save(newClassroom);
    }
}

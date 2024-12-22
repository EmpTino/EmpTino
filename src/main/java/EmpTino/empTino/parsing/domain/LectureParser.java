package EmpTino.empTino.parsing.domain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class LectureParser {

    public static class LectureTime {
        private String day;
        private List<Integer> times;

        public LectureTime(String day, List<Integer> times) {
            this.day = day;
            this.times = times;
        }

        public String getDay() {
            return day;
        }

        public List<Integer> getTimes() {
            return times;
        }
    }

    public static class ClassroomInfo {
        private String buildingName;
        private int floor;
        private String roomNumber;

        public ClassroomInfo(String buildingName, int floor, String roomNumber) {
            this.buildingName = buildingName;
            this.floor = floor;
            this.roomNumber = roomNumber;
        }

        public String getBuildingName() {
            return buildingName;
        }

        public int getFloor() {
            return floor;
        }

        public String getRoomNumber() {
            return roomNumber;
        }
    }
    public static List<LectureTime> parseLectureTimes(String lectureTimeString) {
        List<LectureTime> lectureTimes = new ArrayList<>();

        // 입력 문자열을 괄호 기준으로 분리
        String[] parts = lectureTimeString.split("\\)\\s*");

        for (String part : parts) {
            // 괄호로 끝나는 경우 처리
            if (part.trim().isEmpty()) {
                continue;
            }

            // 강의실 정보 제거
            String timePart = part.contains("(") ? part.substring(0, part.indexOf('(')) : part;

            // 정규식으로 요일과 시간 추출
            Pattern pattern = Pattern.compile("([가-힣]+)\\s*\\[([0-9~]+)]");
            Matcher matcher = pattern.matcher(timePart);

            while (matcher.find()) {
                String day = matcher.group(1); // 요일 추출
                String timeRange = matcher.group(2); // 시간 범위 추출

                List<Integer> times = new ArrayList<>();

                if (timeRange.contains("~")) {
                    // 범위 시간 파싱
                    String[] range = timeRange.split("~");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);
                    for (int i = start; i <= end; i++) {
                        times.add(i);
                    }
                } else {
                    // 단일 시간 파싱
                    times.add(Integer.parseInt(timeRange));
                }

                // 요일과 시간 정보를 LectureTime에 저장
                lectureTimes.add(new LectureTime(day, times));
            }
        }

        return lectureTimes;
    }

    /**
    public static List<LectureTime> parseLectureTimes(String lectureTimeString) {
        List<LectureTime> lectureTimes = new ArrayList<>();

        // 입력 문자열을 괄호를 기준으로 분리
        String[] parts = lectureTimeString.split("\\)\\s*");

        for (String part : parts) {
            // 괄호로 끝나는 경우 처리
            if (part.trim().isEmpty()) {
                continue;
            }

            // 강의실 정보 제거
            String timePart = part.contains("(") ? part.substring(0, part.indexOf('(')) : part;

            // 요일과 시간을 분리
            String[] segments = timePart.split("\\s+");
            String currentDay = null; // 현재 요일을 추적

            for (String segment : segments) {
                if (segment.contains("[") && segment.contains("]")) {
                    // 요일 추출
                    if (currentDay == null) {
                        throw new IllegalArgumentException("유효하지 않은 강의 시간 형식입니다: " + lectureTimeString);
                    }

                    // 시간 범위 추출
                    String timeRange = segment.substring(segment.indexOf('[') + 1, segment.indexOf(']'));
                    List<Integer> times = new ArrayList<>();

                    if (timeRange.contains("~")) {
                        // 범위 시간 파싱
                        String[] range = timeRange.split("~");
                        int start = Integer.parseInt(range[0]);
                        int end = Integer.parseInt(range[1]);
                        for (int i = start; i <= end; i++) {
                            times.add(i);
                        }
                    } else {
                        // 단일 시간 파싱
                        times.add(Integer.parseInt(timeRange));
                    }

                    // 요일과 시간 정보를 LectureTime에 저장
                    lectureTimes.add(new LectureTime(currentDay, times));
                } else {
                    // 요일 정보를 업데이트
                    currentDay = segment;
                }
            }
        }

        return lectureTimes;
    }
**/
    public static ClassroomInfo parseClassroomInfo(String lectureTimeString) {
        int start = lectureTimeString.lastIndexOf("(");
        int end = lectureTimeString.lastIndexOf(")");
        if (start != -1 && end != -1) {
            String classroom = lectureTimeString.substring(start + 1, end);
            String buildingName = classroom.replaceAll("\\d.*", "");
            String roomNumber = classroom.replaceAll("\\D", "");
            int floor = Integer.parseInt(roomNumber.substring(0, 1));
            return new ClassroomInfo(buildingName, floor, roomNumber);
        }
        return null;
    }

}

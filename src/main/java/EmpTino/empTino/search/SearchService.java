package EmpTino.empTino.search;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import EmpTino.empTino.classroom.repository.ClassroomDAORepository;
import EmpTino.empTino.lectureTime.repository.LectureTimeDAORepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final ClassroomDAORepository classroomDAORepository;
    private final LectureTimeDAORepository lectureTimeDAORepository;

    public SearchService(ClassroomDAORepository classroomDAORepository,
                         LectureTimeDAORepository lectureTimeDAORepository) {
        this.classroomDAORepository = classroomDAORepository;
        this.lectureTimeDAORepository = lectureTimeDAORepository;
    }

    public List<ClassroomDAO> findEmptyClassrooms(String building, String time) {
        // Fetch classrooms in the specified building
        List<ClassroomDAO> classroomsInBuilding = classroomDAORepository.findAll()
                .stream()
                .filter(classroom -> classroom.getBuildingName().equalsIgnoreCase(building))
                .collect(Collectors.toList());

        // Fetch occupied classroom IDs at the specified time
        List<UUID> occupiedClassroomIds = lectureTimeDAORepository.findAll()
                .stream()
                .filter(lectureTime -> lectureTime.getTime().equals(time))
                .map(lectureTime -> lectureTime.getLectureId())
                .collect(Collectors.toList());

        // Return classrooms not in the occupied list
        return classroomsInBuilding.stream()
                .filter(classroom -> !occupiedClassroomIds.contains(classroom.getClassroomId()))
                .sorted((c1, c2) -> c2.getClassroomName().compareTo(c1.getClassroomName())) // Sort by classroomName descending
                .collect(Collectors.toList());
    }
}

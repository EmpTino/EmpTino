package EmpTino.empTino.lectureTime.domain;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureTimeDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private String lectureTimeId;

    private String lectureId;

    private String day;

    private int time;

}

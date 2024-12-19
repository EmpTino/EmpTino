package EmpTino.empTino.lectureTime.domain;

import EmpTino.empTino.classroom.domain.ClassroomDAO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LectureTimeDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private UUID lectureTimeId;

    private UUID lectureId;

    private String day;

    private String time;

}

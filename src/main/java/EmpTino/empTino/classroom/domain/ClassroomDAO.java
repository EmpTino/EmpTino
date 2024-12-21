package EmpTino.empTino.classroom.domain;

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
public class ClassroomDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private UUID classroomId;

    private String classroomName;

    private String buildingName;

    private int floor;
}

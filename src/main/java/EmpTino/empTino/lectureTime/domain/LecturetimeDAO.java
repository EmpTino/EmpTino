package EmpTino.empTino.lectureTime.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturetimeDAO {

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

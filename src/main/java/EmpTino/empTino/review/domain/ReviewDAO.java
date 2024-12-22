package EmpTino.empTino.review.domain;

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
public class ReviewDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @ColumnDefault("random_uuid()")
    @Column(updatable = false, nullable = false)
    private String ReviewId;

    private String classroomId;

    private String userName;
    
    //강의실 별점 점수
    private Integer mark;

    //강의실 간단 리뷰글
    private String content;
}

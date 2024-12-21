package EmpTino.empTino.user.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @ColumnDefault("random_uuid()")

    // UUID 형식으로 선언 시 오류가 너무 많이 발생...
    // String 형식으로 UUID 데이터 구조 저장
    @Column(updatable = false, nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;

    private String nickname;
    private String realName;

    // 기본값 : false
    private boolean isAdmin;
}

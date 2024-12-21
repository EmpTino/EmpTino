package EmpTino.empTino.friend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FriendDAO {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @ColumnDefault("random_uuid()")

    // userdao의 userid를 String 형식으로 선언함에 따라
    // frienddao의 id 형식도 String 형식으로 선언
    @Column(updatable = false, nullable = false)
    private String friendId;

    // 친구 요청을 보낸 유저 id
    private String fromUserId;
    // 친구 요청을 받은 유저 id
    private String toUserId;

    private boolean isAccepted;
}

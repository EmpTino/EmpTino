// 서버 시작 시 테스트용 관리자 계정 생성
// username = admin, password = admin123
// isAdmin = 1 (관리자 계정이라는 의미)

package EmpTino.empTino.user.initializer;

import EmpTino.empTino.user.domain.UserDAO;
import EmpTino.empTino.user.repository.UserDAORepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initializeData(UserDAORepository userDAORepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userDAORepository.count() == 0) {
                UserDAO testadmin = UserDAO.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .nickname("Admin")
                        .realName("Administrator")
                        .isAdmin(true)
                        .build();
                userDAORepository.save(testadmin);

                // 일반 사용자 1 생성
                UserDAO user1 = UserDAO.builder()
                        .userName("user1")
                        .password(passwordEncoder.encode("user123"))
                        .nickname("UserOne")
                        .realName("User One")
                        .isAdmin(false)
                        .build();
                userDAORepository.save(user1);

                // 일반 사용자 2 생성
                UserDAO user2 = UserDAO.builder()
                        .userName("user2")
                        .password(passwordEncoder.encode("user123"))
                        .nickname("UserTwo")
                        .realName("User Two")
                        .isAdmin(false)
                        .build();
                userDAORepository.save(user2);
            }
        };
    }
}

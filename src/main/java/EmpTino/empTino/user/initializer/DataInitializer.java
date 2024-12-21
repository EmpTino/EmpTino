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
                UserDAO user = UserDAO.builder()
                        .userName("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .nickname("Admin")
                        .realName("Administrator")
                        .isAdmin(true)
                        .build();

                userDAORepository.save(user);
            }
        };
    }
}

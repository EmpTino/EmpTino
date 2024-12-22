package EmpTino.empTino.login;

import EmpTino.empTino.user.domain.UserDAO;
import EmpTino.empTino.user.repository.UserDAORepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserDAORepository userDAORepository;

    public LoginService(UserDAORepository userDAORepository) {
        this.userDAORepository = userDAORepository;
    }

    public UserDAO findByUserNameAndPassword(String userName, String password) {
        // Optional을 처리하여 값이 없으면 null 반환
        return userDAORepository.findByUserNameAndPassword(userName, password)
                .orElse(null);
    }

    public boolean isUserNameTaken(String userName) {
        return userDAORepository.existsByUserName((userName));
    }
    public UserDAO login(String userName, String password) {
        return userDAORepository.findByUserNameAndPassword(userName, password)
                .orElse(null);
    }

    public void signup(String userName, String password, String nickname, String realName) {
        UserDAO user = UserDAO.builder()
                .userName(userName)
                .password(password)
                .nickname(nickname)
                .realName(realName)
                .build();
        userDAORepository.save(user);
    }
}

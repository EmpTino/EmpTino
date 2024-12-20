package EmpTino.empTino.user.service;

import EmpTino.empTino.user.domain.UserDAO;
import EmpTino.empTino.user.repository.UserDAORepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserDAORepository userDAORepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDAORepository userDAORepository, PasswordEncoder passwordEncoder) {
        this.userDAORepository  = userDAORepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDAO register(UserDAO userDAO) {
        if (userDAORepository.findByUserName(userDAO.getUserName()).isPresent()) {
            throw new IllegalArgumentException("User already exists with this username.");
        }
        userDAO.setPassword(passwordEncoder.encode(userDAO.getPassword())); // 비밀번호 암호화
        return userDAORepository.save(userDAO);
    }

    public UserDAO login(String userName, String password) {
        Optional<UserDAO> userOpt = userDAORepository.findByUserName(userName);

        if (userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPassword())) {
            return userOpt.get(); // 로그인 성공
        } else {
            throw new IllegalArgumentException("Invalid username or password.");
        }
    }
}
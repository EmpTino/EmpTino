package EmpTino.empTino.User.service;

import EmpTino.empTino.User.repository.UserDAORepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAORepository userDAORepository;

    public UserService(UserDAORepository userDAORepository){
        this.userDAORepository = userDAORepository;
    }
}

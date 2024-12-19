package EmpTino.empTino.user.service;

import EmpTino.empTino.user.repository.UserDAORepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAORepository userDAORepository;

    public UserService(UserDAORepository userDAORepository){
        this.userDAORepository = userDAORepository;
    }
}

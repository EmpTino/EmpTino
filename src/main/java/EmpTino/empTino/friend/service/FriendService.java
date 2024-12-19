package EmpTino.empTino.friend.service;

import EmpTino.empTino.friend.repository.FriendDAORepository;
import org.springframework.stereotype.Service;

@Service
public class FriendService {
    private final FriendDAORepository friendDAORepository;

    public FriendService(FriendDAORepository friendDAORepository){
        this.friendDAORepository = friendDAORepository;
    }
}

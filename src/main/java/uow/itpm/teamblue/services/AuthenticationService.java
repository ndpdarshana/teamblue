package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;

@Component
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    public boolean isAuthenticated(User user){
        User dbUser = null;
        if(user.getUsername() != null) {
            dbUser = userRepository.findByUsername(user.getUsername());
        }else if(user.getEmail() != null){
            dbUser = userRepository.findByEmail(user.getEmail());
        }

        if(dbUser != null && dbUser.isAuthenticated(user)){
            return true;
        }
        return false;
    }
}

package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;

@Component
//By default spring use singalton
//to change scope use @Scope(value="prototype")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        User dbUser = userRepository.findByEmail(user.getEmail());
        if(dbUser != null && dbUser.getId() > 0){
            dbUser.setMessage("Email exists");
            return dbUser;
        }
        dbUser = userRepository.findByUsername(user.getUsername());
        if(dbUser != null && dbUser.getId() >0 ){
            dbUser.setMessage("Username exists");
            return dbUser;
        }
        dbUser = userRepository.save(user);
        return dbUser;
    }
}

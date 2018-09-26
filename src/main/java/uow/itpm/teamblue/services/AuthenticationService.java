package uow.itpm.teamblue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.module.security.JwtTokenGenerator;

@Component
public class AuthenticationService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    public boolean isAuthenticated(User user, User dbUser){


        if(dbUser != null && dbUser.isAuthenticated(user)){
            return true;
        }
        return false;
    }

    public AuthenticationTokenResponse getToken(User user){
        User dbUser = null;
        if(user.getUsername() != null) {
            dbUser = userRepository.findByUsername(user.getUsername());
        }else if(user.getEmail() != null){
            dbUser = userRepository.findByEmail(user.getEmail());
        }
        if(dbUser != null && dbUser.isAuthenticated(user)) {
            return jwtTokenGenerator.generate(dbUser);
        }
        return new AuthenticationTokenResponse(HttpStatus.UNAUTHORIZED, null);
    }
}

package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.module.security.JwtTokenGenerator;
import uow.itpm.teamblue.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @PostMapping("/token")
    public @ResponseBody AuthenticationTokenResponse generate(@RequestBody final User user){
        if(user != null) {
            return authenticationService.getToken(user);
//            return "{'status':'success', 'token':'"+jwtTokenGenerator.generate(user)+"'}";
        }
        return new AuthenticationTokenResponse(HttpStatus.UNAUTHORIZED, null);
    }
}

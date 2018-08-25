package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public String generate(@RequestBody final User user){
        if(user != null && authenticationService.isAuthenticated(user)) {
            return "{'status':'success', 'token':'"+jwtTokenGenerator.generate(user)+"'}";
        }
        return "{'status':'Authentication Failed'}";
    }
}

package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.security.JwtTokenGenerator;

@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    JwtTokenGenerator jwtTokenGenerator;

    @PostMapping
    public String generate(@RequestBody final User user){
        return jwtTokenGenerator.generate(user);
    }
}

package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.module.security.JwtValidator;
import uow.itpm.teamblue.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtValidator jwtValidator;


    @PostMapping("/signup")
    public @ResponseBody String addNewUser(@RequestBody User user){
        if(user.getEmail() == null || user.getEmail().equals("") || user.getUsername() == null
                || user.getUsername().equals("")){
            return "{'status':'failed'}";
        }
        userService.createUser(user);
        if(user.getId() != null && user.getId()>0) {
            return "{'status':'success'}";
        }
        return "{'status':'failed'}";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{token}")
    public @ResponseBody User getUser(@PathVariable("token") String token){
        User user = jwtValidator.validate(token);
        user = userRepository.findByUsername(user.getUsername());
        return user;
    }

}

package uow.itpm.teamblue.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody String addNewUser(@RequestBody User user){
        userRepository.save(user);
        return "{'status':'success'}";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
}

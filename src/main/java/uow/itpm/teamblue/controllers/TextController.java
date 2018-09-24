package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uow.itpm.teamblue.model.SubmitResponse;
import uow.itpm.teamblue.model.TextInputRequest;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.services.RequestHandlerService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest")
public class TextController {

    @Autowired
    private RequestHandlerService requestHandlerService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/text")
    public SubmitResponse textIn(HttpServletRequest request,  @RequestBody TextInputRequest body){
        User user = (User) request.getSession().getAttribute("user");
        user = userRepository.findByUsername(user.getUsername());
        return requestHandlerService.textInputHandler(body, user);
    }

}

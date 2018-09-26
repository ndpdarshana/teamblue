package uow.itpm.teamblue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.SubmitResponse;
import uow.itpm.teamblue.model.TextInputRequest;
import uow.itpm.teamblue.model.User;
import uow.itpm.teamblue.model.repo.UserRepository;
import uow.itpm.teamblue.services.RequestHandlerService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class TextController {

    @Autowired
    private RequestHandlerService requestHandlerService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/text")
    public SubmitResponse textIn(HttpServletRequest request,  @RequestBody TextInputRequest body){
        return requestHandlerService.textInputHandler(body, getUser(request));
    }

    @GetMapping("/text/{bookId}")
    public SubmitResponse getResult(HttpServletRequest request, @PathVariable("bookId") Integer bookId){
        return requestHandlerService.getResult(bookId, getUser(request));
    }

    @GetMapping("/documents/all")
    public List<SubmitResponse> getAllDocuments(HttpServletRequest request){
        return requestHandlerService.getAllDocuments(getUser(request));
    }

    private User getUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        return userRepository.findByUsername(user.getUsername());
    }

}

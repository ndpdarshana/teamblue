package uow.itpm.teamblue.controllers;

import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.HealthCheck;

@RestController
public class Health {

    @RequestMapping("/health")
    public HealthCheck check(){
        HealthCheck h = new HealthCheck();
        h.setStatus("success");
        return h;
    }

    @GetMapping("test1")
    public String test(){
        return "WOW";
    }

    @PostMapping("postreq")
    public HealthCheck check(@RequestBody HealthCheck payload){
        System.out.println(payload.getStatus());
        payload.setStatus("New status");
        return payload;
    }

}

package uow.itpm.teamblue.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.HealthCheck;

@RestController
public class Health {
    protected final Log logger = LogFactory.getLog(getClass());

    @RequestMapping("/health")
    public HealthCheck check(){
        logger.debug("Helthy");
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

        payload.setStatus("New status");
        return payload;
    }

}

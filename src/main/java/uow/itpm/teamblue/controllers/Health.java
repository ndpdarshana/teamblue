package uow.itpm.teamblue.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.HealthCheck;

@RestController
@RequestMapping("/rest")
public class Health {
    protected final Log logger = LogFactory.getLog(getClass());

    @GetMapping("/health")
    public HealthCheck check(){
        logger.debug("Helthy");
        HealthCheck h = new HealthCheck();
        h.setStatus("success");
        return h;
    }
}

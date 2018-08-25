package uow.itpm.teamblue.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uow.itpm.teamblue.model.HealthCheck;
import uow.itpm.teamblue.model.Translate;
import uow.itpm.teamblue.services.TranslationService;

@RestController
@RequestMapping("/rest")
public class Health {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    TranslationService translationService;

    @GetMapping("/health")
    public HealthCheck check(){
        logger.debug("Helthy");
        HealthCheck h = new HealthCheck();
        h.setStatus("success");
        return h;
    }

    @GetMapping("/test")
    public HealthCheck translate(){
        Translate translate = new Translate();
        translate.setFromLanguage("en");
        translate.setToLanguage("fr");
        translate.setText("Hello world");
//        translationService.detectLanguage(translate);
        translationService.translate(translate);
        HealthCheck h = new HealthCheck();
        h.setStatus("Success");
        return h;
    }
}

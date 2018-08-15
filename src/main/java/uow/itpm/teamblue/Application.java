package uow.itpm.teamblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import uow.itpm.teamblue.model.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
//@ComponentScan("uow.itpm.teamblue.service")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}

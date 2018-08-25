package uow.itpm.teamblue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import uow.itpm.teamblue.model.FileStorageProperties;
import uow.itpm.teamblue.model.TranslatorProperties;
import uow.itpm.teamblue.module.translator.TranslatorApi;
import uow.itpm.teamblue.module.translator.yandex.YandexTranslator;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class,
        TranslatorProperties.class
})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public TranslatorApi getTranslatorApi(){
        return new YandexTranslator();
    }
}

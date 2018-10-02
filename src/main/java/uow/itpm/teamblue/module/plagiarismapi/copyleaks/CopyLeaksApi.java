package uow.itpm.teamblue.module.plagiarismapi.copyleaks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import uow.itpm.teamblue.model.*;
import uow.itpm.teamblue.module.plagiarismapi.PlagiarismCheckerApi;

import java.util.List;

public class CopyLeaksApi implements PlagiarismCheckerApi {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PlagiarismApiProperties plagiarismApiProperties;

    private PlagiarismApiUser plagiarismApiUser;


    @Override
    public PlagiarismApiUser loginToApi() {
        String url = plagiarismApiProperties.getUrl() + "/account/login-api";
        if(plagiarismApiUser == null) {
            plagiarismApiUser = new PlagiarismApiUser();
        }
        plagiarismApiUser.setEmail(plagiarismApiProperties.getEmail());
        plagiarismApiUser.setApiKey(plagiarismApiProperties.getKey());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        plagiarismApiUser = restTemplate.postForObject(url, plagiarismApiUser, PlagiarismApiUser.class);
        return plagiarismApiUser;
    }

    @Override
    public PlagiarismCheck checkByText(PlagiarismCheck plagiarismCheck){
        if(plagiarismApiUser==null){
            plagiarismApiUser = loginToApi();
        }
        String url = plagiarismApiProperties.getUrl() + "/education/create-by-text";

        String body = plagiarismCheck.getText();
        HttpEntity<String> request = new HttpEntity<>(body, getHeader());
        ResponseEntity<PlagiarismCheck> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, PlagiarismCheck.class);
        if(responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED){
            plagiarismApiUser = loginToApi();
            checkByText(plagiarismCheck);
        }
        responseEntity.getBody().setText(plagiarismCheck.getText());
        responseEntity.getBody().setLanguage(plagiarismCheck.getLanguage());
        plagiarismCheck = responseEntity.getBody();
        return plagiarismCheck;
    }

    @Override
    public PlagiarismCheck status(PlagiarismCheck plagiarismCheck){
        if(plagiarismApiUser==null){
            plagiarismApiUser = loginToApi();
        }

        String url = plagiarismApiProperties.getUrl() + "/education/" + plagiarismCheck.getProcessId() + "/status";

        HttpEntity<String> request = new HttpEntity<>(null, getHeader());
        ResponseEntity<PlagiarismApiStatus> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, PlagiarismApiStatus.class);
        if(responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED){
            plagiarismApiUser = loginToApi();
            plagiarismCheck = status(plagiarismCheck);
        }
        plagiarismCheck.setPlagiarismApiStatus(responseEntity.getBody());
        return plagiarismCheck;
    }

    @Override
    public PlagiarismCheck result(PlagiarismCheck plagiarismCheck){
        if(plagiarismApiUser==null){
            plagiarismApiUser = loginToApi();
        }

        String url = plagiarismApiProperties.getUrl() + "/education/" + plagiarismCheck.getProcessId() + "/result";

        HttpEntity<String> request = new HttpEntity<>(null, getHeader());
        ResponseEntity<List<PlagiarismApiResult>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request,
                new ParameterizedTypeReference<List<PlagiarismApiResult>>(){});
        if(responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED){
            plagiarismApiUser = loginToApi();
            plagiarismCheck = result(plagiarismCheck);
        }

        plagiarismCheck.setPlagiarismApiResultList(responseEntity.getBody());
        return plagiarismCheck;
    }

    public PlagiarismCheck generateReadOnlyKey(PlagiarismCheck plagiarismCheck){
        if(plagiarismApiUser==null){
            plagiarismApiUser = loginToApi();
        }

        String url = plagiarismApiProperties.getUrl() + "/account/permissions/"+ plagiarismCheck.getProcessId() +"/readonly/";

        HttpEntity<String> request = new HttpEntity<>(null, getHeader());
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request,
                new ParameterizedTypeReference<String>(){});
        if(responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED){
            plagiarismApiUser = loginToApi();
            generateReadOnlyKey(plagiarismCheck);
        }

        plagiarismCheck.setKey(responseEntity.getBody());
        return plagiarismCheck;
    }

    private HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + plagiarismApiUser.getAccessToken());
        return headers;
    }
}

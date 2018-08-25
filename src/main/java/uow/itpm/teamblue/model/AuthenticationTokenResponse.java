package uow.itpm.teamblue.model;

import org.springframework.http.HttpStatus;

public class AuthenticationTokenResponse {
    private HttpStatus status;
    private String token;

    public AuthenticationTokenResponse(HttpStatus status, String token){
        this.status = status;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public HttpStatus getStatus() {

        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}

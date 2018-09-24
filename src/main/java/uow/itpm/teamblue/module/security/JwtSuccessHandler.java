package uow.itpm.teamblue.module.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import uow.itpm.teamblue.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("Successfully authenticated");
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = new User();
        user.setUsername(userDetails.getUsername());
        request.getSession().setAttribute("user", user);
    }
}

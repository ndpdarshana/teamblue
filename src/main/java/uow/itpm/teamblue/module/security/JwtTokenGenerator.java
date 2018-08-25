package uow.itpm.teamblue.module.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.AuthenticationTokenResponse;
import uow.itpm.teamblue.model.JwtAuthenticationToken;
import uow.itpm.teamblue.model.User;

@Component
public class JwtTokenGenerator {

    public AuthenticationTokenResponse generate(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        String token = Jwts.builder()
//                .setExpiration(new Date(100))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.secret)
                .compact();
        AuthenticationTokenResponse tokenResponse = new AuthenticationTokenResponse(HttpStatus.CREATED, token);

        return tokenResponse;

    }
}

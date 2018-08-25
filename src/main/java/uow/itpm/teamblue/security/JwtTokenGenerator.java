package uow.itpm.teamblue.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;

@Component
public class JwtTokenGenerator {

    public String generate(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("id", String.valueOf(user.getId()));
        claims.put("role", user.getRole());

        return Jwts.builder()
//                .setExpiration(new Date(100))
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.secret)
                .compact();

    }
}

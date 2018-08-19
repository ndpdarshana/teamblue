package uow.itpm.teamblue.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;
import uow.itpm.teamblue.model.User;

@Component
public class JwtValidator {

    private String secret = "secretkeyfroteamblue";

    public User validate(String token) {

        User user = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            user = new User();
            user.setUsername(body.getSubject());
            user.setId(Integer.parseInt((String) body.get("id")));
            user.setRole((String) body.get("role"));
        }catch(Exception e){
           throw new RuntimeException("Invalid token");
        }

        return user;
    }
}

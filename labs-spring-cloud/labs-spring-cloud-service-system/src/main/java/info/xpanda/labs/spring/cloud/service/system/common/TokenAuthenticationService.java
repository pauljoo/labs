package info.xpanda.labs.spring.cloud.service.system.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class TokenAuthenticationService {
    public static void main(String[] args) {
        String token = Jwts.builder()
                .setSubject("aa")
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS256, "MyJwtSecret")
                .compact();

        System.out.println(token);
        String aa = Jwts.parser()
                .setSigningKey("MyJwtSecret")
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody()
                .getSubject();
        System.out.println(aa);
    }
}

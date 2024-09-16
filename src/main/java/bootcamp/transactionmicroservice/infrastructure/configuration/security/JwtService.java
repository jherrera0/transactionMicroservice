package bootcamp.transactionmicroservice.infrastructure.configuration.security;

import bootcamp.transactionmicroservice.domain.exceptions.MalFormJwtException;
import bootcamp.transactionmicroservice.domain.until.JwtConst;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.security.Key;

public class JwtService {
    @Value("${app-security-key}")
    private String secretKey;

    public String extractUsername(String jwt) {
        try {
            return extractAllClaims(jwt).getSubject();
        } catch (Exception e) {
            throw new MalFormJwtException();
        }
    }
    public String extractRole(String jwt){
        return extractAllClaims(jwt).get(JwtConst.ROLE).toString();
    }

    public Claims extractAllClaims(String jwt) {
        try {
            return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            throw new MalFormJwtException();
        }
    }

    Key generateKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public Long extractUserId(String jwt) {
        return Long.parseLong(extractAllClaims(jwt).get(JwtConst.USER_ID).toString());
    }
}

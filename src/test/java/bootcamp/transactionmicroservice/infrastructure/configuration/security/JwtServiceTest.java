package bootcamp.transactionmicroservice.infrastructure.configuration.security;

import bootcamp.transactionmicroservice.domain.exceptions.MalFormJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private String validJwt;
    private String invalidJwt;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        validJwt = Jwts.builder().setSubject("user").claim("Role", "USER").signWith(Keys.hmacShaKeyFor("mysecretkeymysecretkeymysecretkeymy".getBytes())).compact();
        invalidJwt = "invalidJwt";
    }

    @Test
    void extractUsername_shouldReturnUsername_whenValidJwt() {
        assertEquals("user", jwtService.extractUsername(validJwt));
    }

    @Test
    void extractUsername_shouldThrowMalFormJwtException_whenInvalidJwt() {
        assertThrows(MalFormJwtException.class, () -> jwtService.extractUsername(invalidJwt));
    }

    @Test
    void extractRole_shouldReturnRole_whenValidJwt() {
        assertEquals("USER", jwtService.extractRole(validJwt));
    }

    @Test
    void extractRole_shouldThrowMalFormJwtException_whenInvalidJwt() {
        assertThrows(MalFormJwtException.class, () -> jwtService.extractRole(invalidJwt));
    }

    @Test
    void extractAllClaims_shouldReturnClaims_whenValidJwt() {
        Claims claims = jwtService.extractAllClaims(validJwt);
        assertEquals("user", claims.getSubject());
        assertEquals("USER", claims.get("Role"));
    }

    @Test
    void extractAllClaims_shouldThrowMalFormJwtException_whenInvalidJwt() {
        assertThrows(MalFormJwtException.class, () -> jwtService.extractAllClaims(invalidJwt));
    }
}
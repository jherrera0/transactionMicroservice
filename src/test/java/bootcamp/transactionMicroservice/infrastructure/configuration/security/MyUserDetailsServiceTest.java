package bootcamp.transactionMicroservice.infrastructure.configuration.security;

import bootcamp.transactionMicroservice.domain.exceptions.MalFormJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MyUserDetailsServiceTest {

    private MyUserDetailsService myUserDetailsService;
    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = mock(JwtService.class);
        myUserDetailsService = new MyUserDetailsService(jwtService);
    }

    @Test
    void loadUserByUsername_shouldReturnUserDetails_whenValidJwt() {
        String jwt = "validJwt";
        when(jwtService.extractUsername(jwt)).thenReturn("user");
        when(jwtService.extractRole(jwt)).thenReturn("ROLE_USER");

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwt);

        assertEquals("user", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenInvalidJwt() {
        String jwt = "invalidJwt";
        when(jwtService.extractUsername(jwt)).thenThrow(new MalFormJwtException());

        assertThrows(MalFormJwtException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenJwtServiceThrowsException() {
        String jwt = "jwtWithException";
        when(jwtService.extractUsername(jwt)).thenThrow(new RuntimeException("Unexpected error"));

        assertThrows(RuntimeException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenRoleIsNull() {
        String jwt = "jwtWithNullRole";
        when(jwtService.extractUsername(jwt)).thenReturn("user");
        when(jwtService.extractRole(jwt)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenUsernameIsNull() {
        String jwt = "jwtWithNullUsername";
        when(jwtService.extractUsername(jwt)).thenReturn(null);
        when(jwtService.extractRole(jwt)).thenReturn("ROLE_USER");

        assertThrows(IllegalArgumentException.class, () -> myUserDetailsService.loadUserByUsername(jwt));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenJwtIsNull() {
        assertThrows(IllegalArgumentException.class, () -> myUserDetailsService.loadUserByUsername(null));
    }

    @Test
    void loadUserByUsername_shouldThrowUsernameNotFoundException_whenJwtIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> myUserDetailsService.loadUserByUsername(""));
    }
}